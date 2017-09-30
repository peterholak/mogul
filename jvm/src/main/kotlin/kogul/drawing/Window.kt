package kogul.drawing

import kogul.microdom.Color
import kogul.microdom.Position
import kogul.microdom.setSourceRgb
import sdl2cairo.*
import sdl2cairo.SDL2.*
import sdl2cairo.pango.*
import java.util.concurrent.BlockingQueue

fun l_(o: Any) = (o.javaClass.getMethod("swigValue").invoke(o) as Int).toLong()


class Window(val width: Int, val height: Int, val background: Color = Color.black, val eventListener: BlockingQueue<Event>) {

    val window: SWIGTYPE_p_SDL_Window
    val renderer: SWIGTYPE_p_SDL_Renderer
    val texture: SWIGTYPE_p_SDL_Texture
    val invalidatedEventType: Long
    private var invalidated = true
    var shouldQuit = false; private set

    init {
        SDL_Init(SDL_INIT_EVERYTHING)
        window = SDL_CreateWindow(
                "kogul-jvm",
                SDL_WINDOWPOS_UNDEFINED.toInt(),
                SDL_WINDOWPOS_UNDEFINED.toInt(),
                width,
                height,
                l_(SDL_WindowFlags.SDL_WINDOW_SHOWN)
        ) ?: throw Exception("Failed to create window")
        renderer = SDL_CreateRenderer(
                window,
                -1,
                l_(SDL_RendererFlags.SDL_RENDERER_ACCELERATED) or l_(SDL_RendererFlags.SDL_RENDERER_PRESENTVSYNC)
        ) ?: throw Exception("Failed to initialize renderer")
        texture = SDL_CreateTexture(
                renderer,
                SDL_PIXELFORMAT_ARGB8888.toLong(),
                SDL_TextureAccess.SDL_TEXTUREACCESS_STREAMING.swigValue(),
                width,
                height
        ) ?: throw Exception("Failed to create texture")
        invalidatedEventType = SDL_RegisterEvents(1) // TODO: check result
    }

    fun draw(code: (cairo: Cairo) -> Unit) {
        val pixels = new_voidpp()
        val pitch = new_intp()
        SDL_LockTexture(texture, null, pixels, pitch)
        val surface = cairo_image_surface_create_for_data(
                voidp_to_ucharp(voidpp_value(pixels)),
                cairo_format_t.CAIRO_FORMAT_ARGB32,
                width,
                height,
                intp_value(pitch)
        )
        val cairoT = cairo_create(surface) ?: throw Exception("Failed to initialize cairo")
        val cairo = Cairo(cairoT)
        cairo.setSourceRgb(background)
        cairo.setAntialias(Antialias.None)
        cairo_paint(cairoT)
        code(cairo)
        cairo_destroy(cairoT)
        cairo_surface_destroy(surface)
        SDL_UnlockTexture(texture)
        invalidate()
        delete_voidpp(pixels)
        delete_intp(pitch)
    }

    fun runEfficientEventLoop() {
        val event = SDL_Event()
        while (!shouldQuit) {
            // Blocking wait, there are no animation just yet, so we don't need to keep rendering the texture over and over
            if (SDL_WaitEvent(event) == 0) { throw Exception("Error in SDL_WaitEvent") }
            when (event.type) {
                l_(SDL_EventType.SDL_QUIT) -> quit()
                l_(SDL_EventType.SDL_WINDOWEVENT) -> {
                    when (SDL_WindowEventID.swigToEnum(event.window.event.toInt())) {
                        SDL_WindowEventID.SDL_WINDOWEVENT_EXPOSED -> invalidate()
                        SDL_WindowEventID.SDL_WINDOWEVENT_RESTORED -> invalidate()
                    }
                }
                // TODO: only fire events that someone is subscribed to
                l_(SDL_EventType.SDL_MOUSEBUTTONDOWN) -> {
                    eventListener.put(MouseEvent(MouseDown, Position(event.button.x, event.button.y)))
                }
                l_(SDL_EventType.SDL_MOUSEBUTTONUP) -> {
                    eventListener.put(MouseEvent(MouseUp, Position(event.button.x, event.button.y)))
                }
                l_(SDL_EventType.SDL_MOUSEMOTION) -> {
                    eventListener.put(MouseEvent(MouseMove, Position(event.motion.x, event.motion.y)))
                }
            }

            if (invalidated) {
                SDL_RenderClear(renderer)
                SDL_RenderCopy(renderer, texture, null, null)
                SDL_RenderPresent(renderer)
                invalidated = false
            }
        }
    }

    fun runGameEventLoop() {
        val event = SDL_Event()
        while (!shouldQuit) {
            while(SDL_PollEvent(event) != 0) {
                if (event.type == l_(SDL_EventType.SDL_QUIT)) { quit() }
            }
            SDL_RenderClear(renderer)
            SDL_RenderCopy(renderer, texture, null, null)
            SDL_RenderPresent(renderer)
        }
    }

    fun cleanup() {
        SDL_Quit()
    }

    fun quit() {
        shouldQuit = true
        SDL_PushEvent(SDL_Event().apply { type = l_(SDL_EventType.SDL_QUIT) })
    }

    fun invalidate() {
        invalidated = true
        SDL_PushEvent(createUserEvent(invalidatedEventType))
    }

    fun createUserEvent(eventType: Long): SDL_Event {
        return SDL_Event().apply {
            type = l_(SDL_EventType.SDL_USEREVENT)
            user = SDL_UserEvent().apply {
                type = eventType
            }
        }
    }

    private companion object {
        init {
            System.loadLibrary("sdl_wrap")
            System.loadLibrary("pango_cairo_glib_wrap")
        }
    }
}
