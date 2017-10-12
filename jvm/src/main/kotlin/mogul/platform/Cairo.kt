package mogul.platform

import sdl2cairo.*

class XY(val x: Double, val y: Double)
interface Cairo {
    fun setSourceRgb(r: Number, g: Number, b: Number)
    fun moveTo(x: Number, y: Number)
    fun lineTo(x: Number, y: Number)
    fun stroke()
    fun translate(x: Number, y: Number)
    fun identityMatrix()
    fun save()
    fun restore()
    fun fill()
    fun selectFontFace(family: String, slant: FontSlant = FontSlant.Normal, weight: FontWeight = FontWeight.Normal)
    fun setFontSize(size: Number)
    fun showText(utf8: String)
    fun textExtents(utf8: String): cairo_text_extents_t
    fun textExtentsXY(utf8: String): XY
    fun setLineWidth(width: Number)
    fun setAntialias(antialias: Antialias)
    fun setLineJoin(lineJoin: LineJoin)
    fun rectangle(x: Number, y: Number, width: Number, height: Number)
    fun userToDevice(x: Number, y: Number): XY
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
