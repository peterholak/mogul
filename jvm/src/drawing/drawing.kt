package drawing

import sdl2cairo.*
import sdl2cairo.cairo.*
import sdl2cairo.SDL2.*

fun l_(o: Any) = (o.javaClass.getMethod("swigValue").invoke(o) as Int).toLong()

class Cairo(val cairo: SWIGTYPE_p__cairo) {
    fun setSourceRgb(r: Number, g: Number, b: Number) = cairo_set_source_rgb(cairo, r.toDouble(), g.toDouble(), b.toDouble())
    fun moveTo(x: Number, y: Number) = cairo_move_to(cairo, x.toDouble(), y.toDouble())
    fun lineTo(x: Number, y: Number) = cairo_line_to(cairo, x.toDouble(), y.toDouble())
    fun stroke() = cairo_stroke(cairo)
    fun translate(x: Number, y: Number) = cairo_translate(cairo, x.toDouble(), y.toDouble())
    fun identityMatrix() = cairo_identity_matrix(cairo)
    fun save() = cairo_save(cairo)
    fun restore() = cairo_restore(cairo)
    fun fill() = cairo_fill(cairo)
    fun selectFontFace(family: String, slant: FontSlant = FontSlant.Normal, weight: FontWeight = FontWeight.Normal) =
            cairo_select_font_face(cairo, family, slant.value, weight.value)
    fun setFontSize(size: Number) = cairo_set_font_size(cairo, size.toDouble())
    fun showText(utf8: String) = cairo_show_text(cairo, utf8)
    fun textExtents(utf8: String): cairo_text_extents_t {
        val extents = cairo_text_extents_t()
        cairo_text_extents(cairo, utf8, extents)
        return extents
    }

    enum class FontSlant(val value: cairo_font_slant_t) {
        Normal(cairo_font_slant_t.CAIRO_FONT_SLANT_NORMAL),
        Italic(cairo_font_slant_t.CAIRO_FONT_SLANT_ITALIC),
        Oblique(cairo_font_slant_t.CAIRO_FONT_SLANT_OBLIQUE)
    }

    enum class FontWeight(val value: cairo_font_weight_t) {
        Normal(cairo_font_weight_t.CAIRO_FONT_WEIGHT_NORMAL),
        Bold(cairo_font_weight_t.CAIRO_FONT_WEIGHT_BOLD)
    }
}

class Window(val width: Int, val height: Int) {

    val window: SWIGTYPE_p_SDL_Window
    val renderer: SWIGTYPE_p_SDL_Renderer
    val texture: SWIGTYPE_p_SDL_Texture
    private var invalidated = true
    private var shouldQuit = false

    init {
        SDL_Init(SDL_INIT_EVERYTHING)
        window = SDL_CreateWindow(
                "Hello",
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
        val cairo = cairo_create(surface) ?: throw Exception("Failed to initialize cairo")
        code(Cairo(cairo))
        cairo_destroy(cairo)
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
    }

    companion object {
        init {
            System.loadLibrary("sdl_wrap")
            System.loadLibrary("cairo_wrap")
        }
    }
}
