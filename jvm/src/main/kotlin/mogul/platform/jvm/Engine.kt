package mogul.platform.jvm

import mogul.demo.RunMode
import mogul.demo.runMode
import mogul.microdom.Color
import mogul.microdom.Position
import mogul.platform.*
import sdl2cairo.*
import sdl2cairo.SDL2.*
import sdl2cairo.SDL_EventType.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.coroutines.experimental.buildSequence
import mogul.platform.Engine as EngineInterface

typealias UiThreadCode = () -> Unit

// TODO: all this shit really needs thread safety figured out
// Also this should maybe just be a singleton object, given all the global state in SDL
class Engine(val eventPublisher: EventPublisher) : EngineInterface {
    override val windows = mutableListOf<Window>()
    val windowsById = mutableMapOf<Long, Window>()
    var shouldQuit = false; private set
    private var eventLoopStarted = false
    val userEvents = UserEvents()
    lateinit var uiThread: Thread
    var lastMouseState: MouseState = MouseState(Position(0, 0), setOf())

    // This is handled separately from other events to make it work with QueueEventPublisher. Cleanup of this
    // shit definitely needed.
    override var onEventLoopStarted = mutableListOf<() -> Unit>()

    // Hacky, but easier than messing around with global references in SWIG. Temporary solution.
    // Also the counter is pointless, see the comment in `runOnUiThread`
    val uiThreadCode = ConcurrentHashMap<Int, UiThreadCode>()
    val uiThreadCodeCounter = AtomicInteger(0)

    init {
        SDL_Init(SDL_INIT_EVERYTHING)
    }

    override fun quitting() = shouldQuit

    override fun createWindow(title: String, width: Int, height: Int, background: Color, autoClose: AutoClose): Window {
        val window = Window(
                title,
                userEvents,
                width,
                height,
                background,
                autoClose
        )
        windows.add(window)
        windowsById[window.id] = window
        return window
    }

    override fun destroyWindow(window: mogul.platform.Window) {
        SDL_DestroyWindow((window as Window).window)
        eventPublisher.publish(WindowDestroyedEvent(window))
        windowsById.remove(window.id)
        windows.remove(window)

        if (windows.isEmpty()) {
            quit()
        }
    }

    override fun runOnUiThread(code: () -> Unit) {
        // Really this is quite pointless, I could just use a queue for the lambdas, who cares which lambda is
        // associated with which SDL event, they arrive in order anyway...
        val codeId = uiThreadCodeCounter.incrementAndGet()
        if (codeId == Int.MAX_VALUE) {
            // By the time we reach max, the start of the map will have run long ago
            // TODO: this should also be thread safe...
            uiThreadCodeCounter.set(0)
        }

        // TODO: here could be checks and all, but this is such a hacky solution it's probably going to be completely replaced anyway
        uiThreadCode[codeId] = code
        SDL_PushEvent(userEvents.createRunOnUiThreadEvent(codeId))
    }

    override fun runOnUiThreadAndWait(code: () -> Unit) {
        if (!eventLoopStarted) {
            error("runOnUiThreadAndWait called before the event loop has started.")
        }

        if (runMode == RunMode.Threaded && Thread.currentThread() !== uiThread) {
            // This will all be figured out later :/ :D ...
            val future = CompletableFuture<Unit>()
            val wrapped = {
                code()
                future.complete(Unit).let { Unit }
            }
            runOnUiThread(wrapped)
            future.get()
        }else{
            code()
        }
    }

    override fun runEfficientEventLoop() {
        val event = SDL_Event()
        uiThread = Thread.currentThread()
        eventLoopStarted = true
        notifyOnStart()
        while (!shouldQuit) {
            // Blocking wait, there are no animation just yet, so we don't need to keep rendering the texture over and over
            if (SDL_WaitEvent(event) == 0) { throw Exception("Error in SDL_WaitEvent") }
            processEvent(event)

            windows.forEach { if (it.wasInvalidated()) it.render() }
        }
        eventLoopStarted = false
    }

    override fun quit() {
        shouldQuit = true
        SDL_PushEvent(SDL_Event().apply { type = SDL_QUIT.l })
    }

    override fun runGameEventLoop() {
        val event = SDL_Event()
        uiThread = Thread.currentThread()
        eventLoopStarted = true
        notifyOnStart()
        while (!shouldQuit) {
            while(!shouldQuit && SDL_PollEvent(event) != 0) {
                processEvent(event)
            }
            windows.forEach { it.render() }
        }
        eventLoopStarted = false
    }

    private fun notifyOnStart() {
        if (runMode == RunMode.Threaded) {
            thread(name = "onEventLoopStarted") {
                onEventLoopStarted.forEach { it.invoke() }
            }
        }else{
            onEventLoopStarted.forEach { it.invoke() }
        }
    }

    private fun processEvent(event: SDL_Event) {
        when (event.type) {
            SDL_QUIT.l -> quit()

            SDL_WINDOWEVENT.l ->
                processWindowEvent(event)

            // TODO: only fire events that someone is subscribed to
            // TODO: maybe log warning when the window is not there, but the destroy cycle is kinda shaky right now
            SDL_MOUSEBUTTONDOWN.l -> {
                lastMouseState = mouseStateFromSdl(lastMouseState, event.button)
                eventPublisher.publish(MouseEvent(event.button.window ?: return, MouseDown, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEBUTTONUP.l -> {
                lastMouseState = mouseStateFromSdl(lastMouseState, event.button)
                eventPublisher.publish(MouseEvent(event.button.window ?: return, MouseUp, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEMOTION.l -> {
                lastMouseState = mouseStateFromSdl(event.motion)
                eventPublisher.publish(MouseEvent(event.motion.window ?: return, MouseMove, Position(event.motion.x, event.motion.y)))
            }

            userEvents.invalidatedEventType ->
                windowsById[event.user.windowID]!!.render()

            userEvents.runOnUiThreadEventType -> {
                val codeId = event.user.code
                uiThreadCode[codeId]!!.invoke()
                uiThreadCode.remove(codeId)
            }
        }
    }

    private fun processWindowEvent(event: SDL_Event) {
        // TODO: maybe log warning when the window is not there, but the destroy cycle is kinda shaky right now
        val window = windowsById[event.window.windowID] ?: return
        when (SDL_WindowEventID.swigToEnum(event.window.event.toInt())) {
            SDL_WindowEventID.SDL_WINDOWEVENT_EXPOSED ->
                window.invalidate()

            SDL_WindowEventID.SDL_WINDOWEVENT_RESTORED ->
                window.invalidate()

            SDL_WindowEventID.SDL_WINDOWEVENT_CLOSE -> {
                when(window.autoClose) {
                    AutoClose.Manual -> eventPublisher.publish(WindowCloseRequestedEvent(window))
                    AutoClose.Close, AutoClose.CloseAndDoNotRecreate -> destroyWindow(window)
                }
            }
        }
    }

    private val SDL_MouseButtonEvent.window; get() = windowsById[windowID]
    private val SDL_MouseMotionEvent.window; get() = windowsById[windowID]

    override fun mouseState() = lastMouseState

    override fun cleanup() {
        SDL_Quit()
    }

    private companion object {
        init {
            System.loadLibrary("sdl_wrap")
            System.loadLibrary("cairo_wrap")
        }
    }
}

class UserEvents {
    private val start = SDL_RegisterEvents(2) // TODO: check result
    val invalidatedEventType = start
    val runOnUiThreadEventType = start + 1

    fun createInvalidatedEvent(window: Window) =
        SDL_Event().apply {
            type = SDL_USEREVENT.l
            user = SDL_UserEvent().apply {
                type = invalidatedEventType
                windowID = window.id
            }
        }

    fun createRunOnUiThreadEvent(codeId: Int): SDL_Event {
        return SDL_Event().apply {
            type = SDL_USEREVENT.l
            user = SDL_UserEvent().apply {
                type = runOnUiThreadEventType
                code = codeId
            }
        }
    }
}

fun mouseButtonsFromSdlState(state: Long): Set<MouseButton> {
    return buildSequence {
        if (state and 1L != 0L) { yield(MouseButton.Left) }
        if (state and 2L != 0L) { yield(MouseButton.Middle) }
        if (state and 4L != 0L) { yield(MouseButton.Right) }
    }.toSet()
}

fun mouseButtonFromButtonEvent(button: Short): MouseButton {
    return when(button.toInt()) {
        1 -> MouseButton.Left
        2 -> MouseButton.Middle
        3 -> MouseButton.Right
        else -> error("Invalid button number")
    }
}

fun mouseStateFromSdl(event: SDL_MouseMotionEvent) =
    MouseState(Position(event.x, event.y), mouseButtonsFromSdlState(event.state))

fun mouseStateFromSdl(previousState: MouseState, event: SDL_MouseButtonEvent): MouseState {
    return MouseState(
            Position(event.x, event.y),
            when(event.type) {
                SDL_MOUSEBUTTONDOWN.l -> previousState.buttons + mouseButtonFromButtonEvent(event.button)
                SDL_MOUSEBUTTONUP.l -> previousState.buttons - mouseButtonFromButtonEvent(event.button)
                else -> error("mouseStateFromSdl: Only mouse up/mouse down events are supported.")
            }
    )
}