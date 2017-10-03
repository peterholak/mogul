package mogul.platform.jvm

import mogul.microdom.Color
import mogul.microdom.setSourceRgb
import mogul.platform.Antialias
import mogul.platform.AutoClose
import mogul.platform.Cairo
import sdl2cairo.*
import sdl2cairo.SDL2.*
import sdl2cairo.pango.*
import mogul.platform.Window as WindowInterface

fun l_(o: Any) = (o.javaClass.getMethod("swigValue").invoke(o) as Int).toLong()
val SDL_EventType.l; get() = (javaClass.getMethod("swigValue").invoke(this) as Int).toLong()

class Window(
    title: String,
    val userEvents: UserEvents,
    val width: Int,
    val height: Int,
    val background: Color = Color.black,
    val autoClose: AutoClose = AutoClose.Close
) : WindowInterface {

    val window: SWIGTYPE_p_SDL_Window = SDL_CreateWindow(
            title,
            SDL_WINDOWPOS_UNDEFINED.toInt(),
            SDL_WINDOWPOS_UNDEFINED.toInt(),
            width,
            height,
            l_(SDL_WindowFlags.SDL_WINDOW_SHOWN)
    ) ?: throw Exception("Failed to create window")

    internal val id: Long = SDL_GetWindowID(window)

    val renderer = SDL_CreateRenderer(
            window,
            -1,
            l_(SDL_RendererFlags.SDL_RENDERER_ACCELERATED) or l_(SDL_RendererFlags.SDL_RENDERER_PRESENTVSYNC)
    ) ?: throw Exception("Failed to initialize renderer")

    val texture = SDL_CreateTexture(
            renderer,
            SDL_PIXELFORMAT_ARGB8888.toLong(),
            SDL_TextureAccess.SDL_TEXTUREACCESS_STREAMING.swigValue(),
            width,
            height
    ) ?: throw Exception("Failed to create texture")

    private var invalidated = true
    override fun wasInvalidated() = invalidated

    override fun draw(code: (cairo: Cairo) -> Unit) {
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

    override fun invalidate() {
        invalidated = true
        SDL_PushEvent(userEvents.createInvalidatedEvent(this))
    }

    override fun render() {
        SDL2.SDL_RenderClear(renderer)
        SDL2.SDL_RenderCopy(renderer, texture, null, null)
        SDL2.SDL_RenderPresent(renderer)
        invalidated = false
    }
}
