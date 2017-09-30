package kogul.microdom.primitives

import kogul.drawing.Cairo
import kogul.microdom.*

class Text(
        val text: String,
        override val style: Style = Style()
) : Leaf() {

    override fun draw(cairo: Cairo) {
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        cairo.selectFontFace("sans-serif")
        cairo.setFontSize(style.fontSize ?: 20.0)
        cairo.translate(0, layoutSize(cairo).height)
        cairo.setSourceRgb(style.color ?: Color.black)
        cairo.showText(text)
        cairo.fill()
        cairo.restore()
    }

    override fun defaultInnerSize(cairo: Cairo): Size {
        cairo.selectFontFace("sans-serif")
        cairo.setFontSize(style.fontSize ?: 20.0)
        return cairo.textExtents(text).let {
            Size(it.width.toInt(), it.height.toInt())
        }
    }
}
