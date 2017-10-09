package mogul.microdom.primitives

import mogul.platform.Cairo
import mogul.microdom.*

class Text(
    var text: String,
    override var style: Style = Style(),
    override var events: Events = Events()
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
