package engine

import drawing.Cairo

@DslMarker
annotation class MicroDomMarker

class Box(
        override val style: Style = Style(),
        override val children: List<Node> = emptyList()
) : Container() {

    override fun draw(cairo: Cairo) {
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        drawRect(cairo)
        style.backgroundColor?.let {
            cairo.setSourceRgb(it)
            cairo.fill()
        }
        drawRect(cairo)
        cairo.setSourceRgb(style.borderColor ?: Color.white)
        cairo.stroke()
        children.forEach {
            it.draw(cairo)
        }
        cairo.restore()
    }

    private fun drawRect(cairo: Cairo) {
        val size = layoutSize(cairo)
        cairo.moveTo(0, 0)
        cairo.lineTo(size.width, 0)
        cairo.lineTo(size.width, size.height)
        cairo.lineTo(0, size.height)
        cairo.lineTo(0, 0)
    }

    override fun layoutSize(cairo: Cairo): Size {
        val width = if (style.width != null) {
            style.width
        }else{
            children.sumBy { it.layoutSize(cairo).width }
        } ?: 0

        val height = if (style.height != null) {
            style.height
        }else{
            children.map { it.layoutSize(cairo).height }.max()
        } ?: 0

        return Size(width, height)
    }
}

class Row(
        override val style: Style = Style(),
        override val children: List<Node> = listOf()
) : Container() {

    constructor(vararg children: Node) : this(children = children.toList())
    override fun draw(cairo: Cairo) {
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        children.forEach { child ->
            child.draw(cairo)
            cairo.translate(
                    child.layoutSize(cairo).width +
                    (child.style.margin?.right ?: 0) +
                    (style.padding?.left ?: 0),
                    0
            )
        }
        cairo.restore()
    }

    // TODO: optimize, one pass is enough, or just put this function to Rectangle
    override fun layoutSize(cairo: Cairo): Size {
        if (children.isEmpty()) return Size(0,0)
        return Size(
                width = children.sumBy { it.layoutSize(cairo).width },
                height = children.maxBy { it.layoutSize(cairo).height }!!.layoutSize(cairo).height
        )
    }
}

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
        cairo.setSourceRgb(style.color ?: Color.white)
        cairo.showText(text)
        cairo.fill()
        cairo.restore()
    }

    override fun layoutSize(cairo: Cairo): Size {
        cairo.selectFontFace("sans-serif")
        cairo.setFontSize(style.fontSize ?: 20.0)
        return cairo.textExtents(text).let {
            Size(it.width.toInt(), it.height.toInt())
        }
    }
}
