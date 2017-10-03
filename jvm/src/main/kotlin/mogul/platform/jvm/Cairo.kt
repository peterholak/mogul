package mogul.platform.jvm

import mogul.platform.*
import mogul.platform.Cairo as CairoInterface
import sdl2cairo.*
import sdl2cairo.SDL2.*
import sdl2cairo.pango.*

class Cairo(val cairo: SWIGTYPE_p__cairo) : CairoInterface {
    override fun setSourceRgb(r: Number, g: Number, b: Number) = cairo_set_source_rgb(cairo, r.toDouble(), g.toDouble(), b.toDouble())
    override fun moveTo(x: Number, y: Number) = cairo_move_to(cairo, x.toDouble(), y.toDouble())
    override fun lineTo(x: Number, y: Number) = cairo_line_to(cairo, x.toDouble(), y.toDouble())
    override fun stroke() = cairo_stroke(cairo)
    override fun translate(x: Number, y: Number) = cairo_translate(cairo, x.toDouble(), y.toDouble())
    override fun identityMatrix() = cairo_identity_matrix(cairo)
    override fun save() = cairo_save(cairo)
    override fun restore() = cairo_restore(cairo)
    override fun fill() = cairo_fill(cairo)
    override fun selectFontFace(family: String, slant: FontSlant, weight: FontWeight) =
            cairo_select_font_face(cairo, family, slant.value, weight.value)
    override fun setFontSize(size: Number) = cairo_set_font_size(cairo, size.toDouble())
    override fun showText(utf8: String) = cairo_show_text(cairo, utf8)
    override fun textExtents(utf8: String): cairo_text_extents_t {
        val extents = cairo_text_extents_t()
        cairo_text_extents(cairo, utf8, extents)
        return extents
    }
    override fun setLineWidth(width: Number) = cairo_set_line_width(cairo, width.toDouble())
    override fun setAntialias(antialias: Antialias) = cairo_set_antialias(cairo, antialias.value)
    override fun setLineJoin(lineJoin: LineJoin) = cairo_set_line_join(cairo, lineJoin.value)
    override fun rectangle(x: Number, y: Number, width: Number, height: Number) = cairo_rectangle(cairo, x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
    override fun userToDevice(x: Number, y: Number): XY {
        val xp = new_doublep()
        val yp = new_doublep()
        doublep_assign(xp, x.toDouble())
        doublep_assign(yp, y.toDouble())
        cairo_user_to_device(cairo, xp, yp)
        return XY(doublep_value(xp), doublep_value(yp))
    }
}
