package mogul.drawing

import kotlinx.cinterop.*
import pangocairo.*
import sdl.*
import mogul.microdom.*

class Window(val width: Int, val height: Int, val background: Color = Color.black, val eventListener: (Event) -> Unit) {

    val window: CPointer<SDL_Window>
    val renderer: CPointer<SDL_Renderer>
    val texture: CPointer<SDL_Texture>
    val invalidatedEventType: Uint32
    private var invalidated = false
    var shouldQuit = false; private set

    init {
        SDL_Init(SDL_INIT_EVERYTHING)
        window = SDL_CreateWindow("mogul-native", SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, width, height, SDL_WINDOW_SHOWN)
                ?: throw Exception("Failed to create window")
        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED or SDL_RENDERER_PRESENTVSYNC)
                ?: throw Exception("Failed to initialize renderer")
        texture = SDL_CreateTexture(
                renderer,
                SDL_PIXELFORMAT_ARGB8888,
                SDL_TextureAccess.SDL_TEXTUREACCESS_STREAMING.value,
                width,
                height
        ) ?: throw Exception("Failed to create texture")
        invalidatedEventType = SDL_RegisterEvents(1) // TODO: check result
    }

    fun draw(code: (cairo: Cairo) -> Unit) {
        memScoped {
            val pixels = alloc<COpaquePointerVar>()
            val pitch = alloc<IntVar>()
            SDL_LockTexture(texture, null, pixels.ptr, pitch.ptr)
            val surface = cairo_image_surface_create_for_data(
                    pixels.value!!.reinterpret<ByteVar>(),
                    CAIRO_FORMAT_ARGB32,
                    width,
                    height,
                    pitch.value
            )
            val cairoT = cairo_create(surface) ?: throw Exception("Failed to initialize cairo")
            val cairo = Cairo(cairoT)
            cairo.setSourceRgb(background)
            cairo.setAntialias(Antialias.None)
            cairo_paint(cairoT)
            code(cairo)
            SDL_UnlockTexture(texture)
            invalidate()
        }
    }

    fun runEfficientEventLoop() {
        memScoped {
            val event = alloc<SDL_Event>()
            while (!shouldQuit) {
                // Blocking wait, there are no animation just yet, so we don't need to keep rendering the texture over and over
                if (SDL_WaitEvent(event.ptr.reinterpret()) == 0) { throw Exception("Error in SDL_WaitEvent") }
                when (event.type) {
                    SDL_QUIT -> quit()
                    SDL_WINDOWEVENT -> {
                        when (event.window.event.toInt()) {
                            SDL_WindowEventID.SDL_WINDOWEVENT_EXPOSED.value -> invalidate()
                            SDL_WindowEventID.SDL_WINDOWEVENT_RESTORED.value -> invalidate()
                        }
                    }

                    SDL_MOUSEBUTTONDOWN ->
                        eventListener.invoke(MouseEvent(MouseDown, Position(event.button.x, event.button.y)))

                    SDL_MOUSEBUTTONUP ->
                        eventListener.invoke(MouseEvent(MouseUp, Position(event.button.x, event.button.y)))

                    SDL_MOUSEMOTION ->
                        eventListener.invoke(MouseEvent(MouseMove, Position(event.motion.x, event.motion.y)))
                }

                if (invalidated) {
                    SDL_RenderClear(renderer)
                    SDL_RenderCopy(renderer, texture, null, null)
                    SDL_RenderPresent(renderer)
                    invalidated = false
                }
            }
        }
    }

    fun runGameEventLoop() {
        memScoped {
            val event = alloc<SDL_Event>()
            while (!shouldQuit) {
                while (SDL_PollEvent(event.ptr.reinterpret()) != 0) {
                    if (event.type == SDL_QUIT) { quit() }
                }
                SDL_RenderClear(renderer)
                SDL_RenderCopy(renderer, texture, null, null)
                SDL_RenderPresent(renderer)
            }
        }
    }

    fun cleanup() {
        SDL_Quit()
    }

    fun invalidate() {
        invalidated = true
        memScoped {
            val event = alloc<SDL_Event>()
            event.type = SDL_USEREVENT
            event.user.type = invalidatedEventType
            SDL_PushEvent(event.ptr)
        }
    }

    fun quit() {
        shouldQuit = true
        memScoped {
            val event = alloc<SDL_Event>()
            event.type = SDL_QUIT
            SDL_PushEvent(event.ptr)
        }
    }
}
