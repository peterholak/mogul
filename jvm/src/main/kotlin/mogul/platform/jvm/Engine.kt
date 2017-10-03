package mogul.platform.jvm

import mogul.microdom.Color
import mogul.microdom.Position
import mogul.platform.*
import sdl2cairo.*
import sdl2cairo.SDL_EventType.*
import sdl2cairo.SDL2.*
import mogul.platform.Engine as EngineInterface

class Engine(val eventPublisher: EventPublisher) : EngineInterface {
    override val windows = mutableListOf<Window>()
    val windowsById = mutableMapOf<Long, Window>()
    var shouldQuit = false; private set
    val invalidatedEventType: Long
    val userEvents = UserEvents()

    init {
        SDL_Init(SDL_INIT_EVERYTHING)
        invalidatedEventType = SDL_RegisterEvents(1) // TODO: check result
    }

    override fun quitting() = shouldQuit

    override fun createWindow(title: String, width: Int, height: Int, background: Color): Window {
        val window = Window(
                title,
                userEvents,
                width,
                height,
                background
        )
        windows.add(window)
        windowsById[window.id] = window
        return window
    }

    override fun runEfficientEventLoop() {
        val event = SDL_Event()
        while (!shouldQuit) {
            // Blocking wait, there are no animation just yet, so we don't need to keep rendering the texture over and over
            if (SDL2.SDL_WaitEvent(event) == 0) { throw Exception("Error in SDL_WaitEvent") }
            processEvent(event)

            windows.forEach { if (it.wasInvalidated()) it.render() }
        }
    }

    override fun quit() {
        shouldQuit = true
        SDL2.SDL_PushEvent(SDL_Event().apply { type = SDL_QUIT.l })
    }

    override fun runGameEventLoop() {
        val event = SDL_Event()
        while (!shouldQuit) {
            while(SDL2.SDL_PollEvent(event) != 0) {
                processEvent(event)
            }
            windows.forEach { it.render() }
        }
    }

    fun processEvent(event: SDL_Event) {
        when (event.type) {
            SDL_QUIT.l -> quit()
            SDL_WINDOWEVENT.l -> {
                when (SDL_WindowEventID.swigToEnum(event.window.event.toInt())) {
                    SDL_WindowEventID.SDL_WINDOWEVENT_EXPOSED -> windowsById[event.window.windowID]!!.invalidate()
                    SDL_WindowEventID.SDL_WINDOWEVENT_RESTORED -> windowsById[event.window.windowID]!!.invalidate()
                }
            }

            // TODO: only fire events that someone is subscribed to
            SDL_MOUSEBUTTONDOWN.l -> {
                eventPublisher.publish(MouseEvent(event.button.window, MouseDown, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEBUTTONUP.l -> {
                eventPublisher.publish(MouseEvent(event.button.window, MouseUp, Position(event.button.x, event.button.y)))
            }
            SDL_MOUSEMOTION.l -> {
                eventPublisher.publish(MouseEvent(event.motion.window, MouseMove, Position(event.motion.x, event.motion.y)))
            }

            SDL_USEREVENT.l -> {
                if (event.user.type == invalidatedEventType) {
                    windowsById[event.user.windowID]!!.render()
                }
            }
        }
    }

    val SDL_MouseButtonEvent.window; get() = windowsById[windowID]!!
    val SDL_MouseMotionEvent.window; get() = windowsById[windowID]!!

    override fun cleanup() {
        SDL_Quit()
    }

    private companion object {
        init {
            System.loadLibrary("sdl_wrap")
            System.loadLibrary("pango_cairo_glib_wrap")
        }
    }
}

class UserEvents {
    val invalidatedEventType = SDL_RegisterEvents(1) // TODO: check result

    fun createInvalidatedEvent(window: Window) =
        SDL_Event().apply {
            type = SDL_USEREVENT.l
            user = SDL_UserEvent().apply {
                type = invalidatedEventType
                windowID = window.id
            }
        }
}
