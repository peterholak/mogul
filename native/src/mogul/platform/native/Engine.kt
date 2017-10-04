package mogul.platform.native

import mogul.microdom.Color
import mogul.microdom.Position
import mogul.platform.*
import kotlinx.cinterop.*
import sdl.*
import pangocairo.*
import mogul.platform.Engine as EngineInterface

class Engine(val eventPublisher: EventPublisher) : EngineInterface {
    override val windows = mutableListOf<mogul.platform.Window>()
    val windowsById = mutableMapOf<Long, Window>()
    var shouldQuit = false; private set
    private var eventLoopStarted = false
    val userEvents = UserEvents()

    // This is handled separately from other events to make it work with QueueEventPublisher. Cleanup of this
    // shit definitely needed.
    override var onEventLoopStarted = mutableListOf<() -> Unit>()

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
        // For now, native version is single-threaded only
        code()
    }

    override fun runOnUiThreadAndWait(code: () -> Unit) {
        // For now, native version is single-threaded only
        code()
    }

    override fun runEfficientEventLoop() {
        memScoped {
            val event = alloc<SDL_Event>()
            eventLoopStarted = true
            notifyOnStart()
            while (!shouldQuit) {
                // Blocking wait, there are no animation just yet, so we don't need to keep rendering the texture over and over
                if (SDL_WaitEvent(event.ptr.reinterpret()) == 0) { throw Exception("Error in SDL_WaitEvent") }
                processEvent(event)

                windows.forEach { if (it.wasInvalidated()) it.render() }
            }
            eventLoopStarted = false
        }
    }

    override fun quit() {
        shouldQuit = true
        memScoped {
            val event = alloc<SDL_Event>()
            event.type = SDL_QUIT
            SDL_PushEvent(event.ptr)
        }
    }

    override fun runGameEventLoop() {
        memScoped {
            val event = alloc<SDL_Event>()
            eventLoopStarted = true
            notifyOnStart()
            while (!shouldQuit) {
                while (!shouldQuit && SDL_PollEvent(event.ptr.reinterpret()) != 0) {
                    processEvent(event)
                }
                windows.forEach { it.render() }
            }
            eventLoopStarted = false
        }
    }

    private fun notifyOnStart() {
        onEventLoopStarted.forEach { it.invoke() }
    }

    private fun processEvent(event: SDL_Event) {
        when (event.type) {
            SDL_QUIT -> quit()

            SDL_WINDOWEVENT ->
                processWindowEvent(event)

            // TODO: only fire events that someone is subscribed to
            // TODO: maybe log warning when the window is not there, but the destroy cycle is kinda shaky right now
            SDL_MOUSEBUTTONDOWN -> {
                eventPublisher.publish(MouseEvent(event.button.window ?: return, MouseDown, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEBUTTONUP -> {
                eventPublisher.publish(MouseEvent(event.button.window ?: return, MouseUp, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEMOTION -> {
                eventPublisher.publish(MouseEvent(event.motion.window ?: return, MouseMove, Position(event.motion.x, event.motion.y)))
            }

            userEvents.invalidatedEventType ->
                windowsById[event.user.windowID.toLong()]?.render()
        }
    }

    private fun processWindowEvent(event: SDL_Event) {
        // TODO: maybe log warning when the window is not there, but the destroy cycle is kinda shaky right now
        val window = windowsById[event.window.windowID.toLong()] ?: return
        when (event.window.event.toInt()) {
            SDL_WindowEventID.SDL_WINDOWEVENT_EXPOSED.value ->
                window.invalidate()

            SDL_WindowEventID.SDL_WINDOWEVENT_RESTORED.value ->
                window.invalidate()

            SDL_WindowEventID.SDL_WINDOWEVENT_CLOSE.value -> {
                when(window.autoClose) {
                    AutoClose.Manual -> eventPublisher.publish(WindowCloseRequestedEvent(window))
                    AutoClose.Close, AutoClose.CloseAndDoNotRecreate -> destroyWindow(window)
                }
            }
        }
    }

    private val SDL_MouseButtonEvent.window; get() = windowsById[windowID.toLong()]
    private val SDL_MouseMotionEvent.window; get() = windowsById[windowID.toLong()]

    override fun cleanup() {
        SDL_Quit()
    }
}

class UserEvents {
    private val start = SDL_RegisterEvents(1) // TODO: check result
    val invalidatedEventType = start

    fun pushInvalidatedEvent(window: Window) {
        memScoped {
            val event = alloc<SDL_Event>()
            event.type = SDL_USEREVENT
            event.user.type = invalidatedEventType
            SDL_PushEvent(event.ptr)
        }
    }
}
