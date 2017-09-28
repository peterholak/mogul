package engine.primitives

import drawing.Cairo
import engine.*

class Box(
        override val style: Style = Style(),
        override val children: List<Node> = emptyList()
) : Container() {

    override fun draw(cairo: Cairo) {
        val size = layoutSize(cairo)
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        fillRectangle(cairo, size)
        drawBorder(cairo, size)
        children.forEach {
            it.draw(cairo)
        }
        cairo.restore()
    }

    private fun drawBorder(cairo: Cairo, size: Size) {
        val border = style.border ?: return

        if (border.width.allEqual() && border.color.allEqual()) {
            cairo.rectangle(0, 0, size.width, size.height)
            cairo.setSourceRgb(border.color.top ?: Color.black)
        }else{
            val width = border.width
            val color = border.color
            drawSingleBorder(cairo, width.top, color.top, 0, 0, size.width, 0, -width.left, width.right)
            drawSingleBorder(cairo, width.right, color.right, size.width, 0, size.width, size.height, -width.top, width.bottom)
            drawSingleBorder(cairo, width.bottom, color.bottom, size.width, size.height, 0, size.height, width.right, -width.left)
            drawSingleBorder(cairo, width.left, color.left, 0, size.height, 0, 0, width.bottom, -width.top)
        }
        cairo.stroke()
    }

    private fun drawSingleBorder(
            cairo: Cairo,
            borderWidth: Int,
            color: Color?,
            xFrom: Int,
            yFrom: Int,
            xTo: Int,
            yTo: Int,
            extraFrom: Int,
            extraTo: Int)
    {
        if (borderWidth != 0 || color != null) {
            val horizontal = (yFrom == yTo)
            val extraXFrom = if (horizontal) extraFrom/2 else 0
            val extraYFrom = if (horizontal) 0 else extraFrom/2
            val extraXTo = if (horizontal) extraTo/2 else 0
            val extraYTo = if (horizontal) 0 else extraTo/2

            cairo.setLineWidth(borderWidth)
            cairo.setSourceRgb(color ?: Color.black)
            cairo.moveTo(xFrom + extraXFrom, yFrom + extraYFrom)
            cairo.lineTo(xTo + extraXTo, yTo + extraYTo)
            cairo.stroke()
        }
    }

    private fun fillRectangle(cairo: Cairo, size: Size) {
        style.backgroundColor?.let {
            cairo.rectangle(0, 0, size.width, size.height)
            cairo.setSourceRgb(it)
            cairo.fill()
        }
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