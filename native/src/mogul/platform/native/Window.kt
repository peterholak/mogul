package mogul.platform.native

import kotlinx.cinterop.*
import pangocairo.*
import sdl.*
import mogul.microdom.*
import mogul.platform.*
import mogul.platform.Cairo
import mogul.platform.Window as WindowInterface

class Window(
    title: String,
    val userEvents: UserEvents,
    val width: Int,
    val height: Int,
    val background: Color = Color.black,
    val autoClose: AutoClose = AutoClose.Close
) : WindowInterface {

    val window = SDL_CreateWindow(
            title,
            SDL_WINDOWPOS_UNDEFINED,
            SDL_WINDOWPOS_UNDEFINED,
            width,
            height,
            SDL_WINDOW_SHOWN
    ) ?: throw Exception("Failed to create window")

    internal val id: Long = SDL_GetWindowID(window).toLong()

    val renderer = SDL_CreateRenderer(
            window,
            -1,
            SDL_RENDERER_ACCELERATED or SDL_RENDERER_PRESENTVSYNC
    ) ?: throw Exception("Failed to initialize renderer")

    val texture = SDL_CreateTexture(
            renderer,
            SDL_PIXELFORMAT_ARGB8888,
            SDL_TextureAccess.SDL_TEXTUREACCESS_STREAMING.value,
            width,
            height
    ) ?: throw Exception("Failed to create texture")

    private var invalidated = false
    override fun wasInvalidated() = invalidated

    override fun draw(code: (cairo: Cairo) -> Unit) {
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

    override fun invalidate() {
        invalidated = true
        userEvents.pushInvalidatedEvent(this)
    }

    override fun render() {
        SDL_RenderClear(renderer)
        SDL_RenderCopy(renderer, texture, null, null)
        SDL_RenderPresent(renderer)
        invalidated = false
    }
}
