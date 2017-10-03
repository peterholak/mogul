package mogul.microdom.primitives

import javafx.geometry.Pos
import mogul.platform.Cairo
import mogul.microdom.*

class Box(
    override val style: Style = Style(),
    override val events: Events = Events(),
    children: List<Node> = emptyList()
) : Container(children) {

    override fun draw(cairo: Cairo) {
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

    private fun drawBorder(cairo: Cairo, size: Size) {
        val border = style.border ?: return

        if (border.width.allEqual() && border.color.allEqual()) {
            val padding = style.padding ?: BoxSizes.zero
            cairo.rectangle(
                    -padding.left,
                    -padding.top,
                    size.width + padding.left + padding.right,
                    size.height + padding.top + padding.bottom
            )
            cairo.setSourceRgb(border.color.top ?: Color.black)
            cairo.setLineWidth(border.width.top)
        }else{
            val width = border.width
            val color = border.color
            val padding = style.padding ?: BoxSizes.zero
            val xLeft = -padding.left
            val xRight = size.width + padding.right
            val yTop = -padding.top
            val yBottom = size.height + padding.bottom
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.top,
                    color = color.top,
                    from = Position(xLeft, yTop),
                    to = Position(xRight, yTop),
                    extraFrom = -width.left,
                    extraTo = width.right
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.right,
                    color = color.right,
                    from = Position(xRight, yTop),
                    to = Position(xRight, yBottom),
                    extraFrom = -width.top,
                    extraTo = width.bottom
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.bottom,
                    color = color.bottom,
                    from = Position(xRight, yBottom),
                    to = Position(xLeft, yBottom),
                    extraFrom = width.right,
                    extraTo = -width.left
            )
            drawSingleBorder(
                    cairo = cairo,
                    borderWidth = width.left,
                    color = color.left,
                    from = Position(xLeft, yBottom),
                    to = Position(xLeft, yTop),
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
        style.backgroundColor?.let {
            cairo.rectangle(0, 0, size.width, size.height)
            cairo.setSourceRgb(it)
            cairo.fill()
        }
    }

    override fun defaultInnerSize(cairo: Cairo): Size {
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
