package kogul.drawing

import kotlinx.cinterop.*
import pangocairo.*

class Cairo(val cairo: CPointer<cairo_t>) {
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
        // TODO: memory leak, maybe wrap this in a refcounted holder? just copy values?
        val extents = nativeHeap.alloc<cairo_text_extents_t>()
        cairo_text_extents(cairo, utf8, extents.ptr)
        return extents
    }
    fun setLineWidth(width: Number) = cairo_set_line_width(cairo, width.toDouble())
    fun setAntialias(antialias: Antialias) = cairo_set_antialias(cairo, antialias.value)
    fun setLineJoin(lineJoin: LineJoin) = cairo_set_line_join(cairo, lineJoin.value)
    fun rectangle(x: Number, y: Number, width: Number, height: Number) = cairo_rectangle(cairo, x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
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

enum class Antialias(val value: cairo_antialias_t) {
    Best(cairo_antialias_t.CAIRO_ANTIALIAS_BEST),
    Default(cairo_antialias_t.CAIRO_ANTIALIAS_DEFAULT),
    Fast(cairo_antialias_t.CAIRO_ANTIALIAS_FAST),
    Good(cairo_antialias_t.CAIRO_ANTIALIAS_GOOD),
    Gray(cairo_antialias_t.CAIRO_ANTIALIAS_GRAY),
    None(cairo_antialias_t.CAIRO_ANTIALIAS_NONE),
    Subpixel(cairo_antialias_t.CAIRO_ANTIALIAS_SUBPIXEL)
}

enum class LineJoin(val value: cairo_line_join_t) {
    Miter(cairo_line_join_t.CAIRO_LINE_JOIN_MITER),
    Round(cairo_line_join_t.CAIRO_LINE_JOIN_ROUND),
    Bevel(cairo_line_join_t.CAIRO_LINE_JOIN_BEVEL)
}