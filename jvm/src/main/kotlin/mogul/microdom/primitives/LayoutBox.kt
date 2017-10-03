package mogul.microdom.primitives

import mogul.platform.Cairo
import mogul.microdom.*

sealed class Direction {
    abstract fun postDrawTranslateX(cairo: Cairo, child: Node, spacing: Int): Int
    abstract fun postDrawTranslateY(cairo: Cairo, child: Node, spacing: Int): Int
    abstract fun totalWidth(cairo: Cairo, children: List<Node>): Int
    abstract fun totalHeight(cairo: Cairo, children: List<Node>): Int
}

object HorizontalDirection : Direction() {
    override fun totalWidth(cairo: Cairo, children: List<Node>) =
            children.sumBy { it.layoutSize(cairo).width }

    override fun totalHeight(cairo: Cairo, children: List<Node>) =
            children.maxBy { it.layoutSize(cairo).height }!!.layoutSize(cairo).height

    override fun postDrawTranslateX(cairo: Cairo, child: Node, spacing: Int) =
            child.layoutSize(cairo).width + spacing

    override fun postDrawTranslateY(cairo: Cairo, child: Node, spacing: Int) = 0

}

object VerticalDirection : Direction() {
    override fun totalWidth(cairo: Cairo, children: List<Node>) =
            children.maxBy { it.layoutSize(cairo).width }!!.layoutSize(cairo).width

    override fun totalHeight(cairo: Cairo, children: List<Node>) =
            children.sumBy { it.layoutSize(cairo).height }

    override fun postDrawTranslateX(cairo: Cairo, child: Node, spacing: Int) = 0

    override fun postDrawTranslateY(cairo: Cairo, child: Node, spacing: Int) =
            child.layoutSize(cairo).height + spacing
}

// The spacing should be later replaced by a flexbox-like style property
class LayoutBox(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        override val style: Style = Style(),
        override val events: Events = Events(),
        children: List<Node> = emptyList()
) : Container(children) {

    override fun draw(cairo: Cairo) {
        cairo.save()
        style.margin?.let { cairo.translate(it.left, it.top) }
        children.forEach { child ->
            child.topLeft = cairo.userToDevice(0, 0).position
            child.draw(cairo)
            cairo.translate(
                    x = direction.postDrawTranslateX(cairo, child, spacing),
                    y = direction.postDrawTranslateY(cairo, child, spacing)
            )
        }
        cairo.restore()
    }

    // TODO: optimize, one pass is enough, or just put this function to Rectangle
    override fun defaultInnerSize(cairo: Cairo): Size {
        if (children.isEmpty()) return Size(0, 0)
        return Size(
                direction.totalWidth(cairo, children),
                direction.totalHeight(cairo, children)
        )
    }
}
