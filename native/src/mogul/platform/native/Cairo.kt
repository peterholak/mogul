package mogul.platform.native

import kotlinx.cinterop.*
import pangocairo.*
import mogul.platform.*
import mogul.platform.Cairo as CairoInterface

class Cairo(val cairo: CPointer<cairo_t>): CairoInterface {
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
        // TODO: memory leak, maybe wrap this in a refcounted holder? just copy values?
        val extents = nativeHeap.alloc<cairo_text_extents_t>()
        cairo_text_extents(cairo, utf8, extents.ptr)
        return extents
    }
    override fun setLineWidth(width: Number) = cairo_set_line_width(cairo, width.toDouble())
    override fun setAntialias(antialias: Antialias) = cairo_set_antialias(cairo, antialias.value)
    override fun setLineJoin(lineJoin: LineJoin) = cairo_set_line_join(cairo, lineJoin.value)
    override fun rectangle(x: Number, y: Number, width: Number, height: Number) = cairo_rectangle(cairo, x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
    override fun userToDevice(x: Number, y: Number): XY {
        memScoped {
            val xp = alloc<DoubleVar>()
            xp.value = x.toDouble()
            val yp = alloc<DoubleVar>()
            yp.value = y.toDouble()
            cairo_user_to_device(cairo, xp.ptr, yp.ptr)
            return XY(xp.value, yp.value)
        }
    }
}
