package mogul.microdom.primitives

import mogul.platform.Cairo
import mogul.microdom.*

class Box(
    override var style: Style = Style(),
    override var hoverStyle: Style? = null,
    override var mouseDownStyle: Style? = null,
    override val events: Events = Events(),
    children: List<Node> = emptyList()
) : Container(children) {

    private fun drawDebugInputRectangle(cairo: Cairo) {
        cairo.save()
        cairo.identityMatrix()
        val br = boundingRectangle()!!
        cairo.setSourceRgb(0x00FF00.color)
        cairo.setLineWidth(3)
        cairo.rectangle(br.left, br.top, br.width, br.height)
        cairo.stroke()
        cairo.restore()
    }

    override fun draw(cairo: Cairo) {
//        drawDebugInputRectangle(cairo)

        val style = effectiveStyle()

        val size = innerSize(cairo)
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        fillRectangle(cairo, size)
        drawBorder(cairo, size)
        children.forEach {
            it.topLeft = cairo.userToDevice(0, 0).position
            it.draw(cairo)
        }
        cairo.restore()
    }

    fun rectangleWithPadding(size: Size): Rectangle {
        val padding = style.padding ?: EdgeSizes.zero
        return Rectangle(
                Position(-padding.left, -padding.top),
                Size(size.width + padding.left + padding.right, size.height + padding.top + padding.bottom)
        )
    }

    private fun drawBorder(cairo: Cairo, size: Size) {
        val style = effectiveStyle()

        val border = style.border ?: return

        if (border.width.allEqual() && border.color.allEqual()) {
            val padding = style.padding ?: EdgeSizes.zero
            val corners = rectangleWithPadding(size)
            cairo.rectangle(corners.left, corners.top, corners.width, corners.height)
            cairo.setSourceRgb(border.color.top ?: Color.black)
            cairo.setLineWidth(border.width.top)
        }else{
            val width = border.width
            val color = border.color
            val corners = rectangleWithPadding(size)
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.top,
                    color = color.top,
                    from = corners.topLeft,
                    to = corners.topRight,
                    extraFrom = -width.left,
                    extraTo = width.right
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.right,
                    color = color.right,
                    from = corners.topRight,
                    to = corners.bottomRight,
                    extraFrom = -width.top,
                    extraTo = width.bottom
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.bottom,
                    color = color.bottom,
                    from = corners.bottomRight,
                    to = corners.bottomLeft,
                    extraFrom = width.right,
                    extraTo = -width.left
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.left,
                    color = color.left,
                    from = corners.bottomLeft,
                    to = corners.topLeft,
                    extraFrom = width.bottom,
                    extraTo = -width.top
            )
        }
        cairo.stroke()
    }

    private fun drawSingleBorder(
        cairo: Cairo,
        borderWidth: Int,
        color: Color?,
        from: Position,
        to: Position,
        extraFrom: Int,
        extraTo: Int)
    {
        if (borderWidth != 0 || color != null) {
            val horizontal = (from.y == to.y)

            // When platformJvm lines individually, the path ends at the exact point (no line joins),
            // which makes rectangle corners look ugly as fuck. This addresses that problem.
            val extraXFrom = if (horizontal) extraFrom/2 else 0
            val extraYFrom = if (horizontal) 0 else extraFrom/2
            val extraXTo = if (horizontal) extraTo/2 else 0
            val extraYTo = if (horizontal) 0 else extraTo/2

            cairo.setLineWidth(borderWidth)
            cairo.setSourceRgb(color ?: Color.black)
            cairo.moveTo(from.x + extraXFrom, from.y + extraYFrom)
            cairo.lineTo(to.x + extraXTo, to.y + extraYTo)
            cairo.stroke()
        }
    }

    private fun fillRectangle(cairo: Cairo, size: Size) {
        val style = effectiveStyle()
        val corners = rectangleWithPadding(size)

        style.backgroundColor?.let {
            cairo.rectangle(corners.left, corners.top, corners.width, corners.height)
            cairo.setSourceRgb(it)
            cairo.fill()
        }
    }

    override fun defaultInnerSize(cairo: Cairo): Size {
        val style = effectiveStyle()

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
