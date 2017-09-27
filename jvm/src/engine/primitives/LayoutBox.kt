package engine.primitives

import drawing.Cairo
import engine.Container
import engine.Node
import engine.Size
import engine.Style

sealed class Direction {
    abstract fun postDrawTranslateX(cairo: Cairo, child: Node, style: Style): Int
    abstract fun postDrawTranslateY(cairo: Cairo, child: Node, style: Style): Int
    abstract fun totalWidth(cairo: Cairo, children: List<Node>): Int
    abstract fun totalHeight(cairo: Cairo, children: List<Node>): Int
}

object HorizontalDirection : Direction() {
    override fun totalWidth(cairo: Cairo, children: List<Node>) =
            children.sumBy { it.layoutSize(cairo).width }

    override fun totalHeight(cairo: Cairo, children: List<Node>) =
            children.maxBy { it.layoutSize(cairo).height }!!.layoutSize(cairo).height

    override fun postDrawTranslateX(cairo: Cairo, child: Node, style: Style) =
            child.layoutSize(cairo).width +
            (child.style.margin?.right ?: 0) +
            (style.padding?.left ?: 0)

    override fun postDrawTranslateY(cairo: Cairo, child: Node, style: Style) = 0

}

object VerticalDireciton : Direction() {
    override fun totalWidth(cairo: Cairo, children: List<Node>) =
            children.maxBy { it.layoutSize(cairo).width }!!.layoutSize(cairo).width

    override fun totalHeight(cairo: Cairo, children: List<Node>) =
            children.sumBy { it.layoutSize(cairo).height }

    override fun postDrawTranslateX(cairo: Cairo, child: Node, style: Style) = 0

    override fun postDrawTranslateY(cairo: Cairo, child: Node, style: Style) =
            child.layoutSize(cairo).height +
            (child.style.margin?.bottom ?: 0) +
            (style.padding?.top ?: 0)

}

class LayoutBox(
        val direction: Direction = HorizontalDirection,
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
                    x = direction.postDrawTranslateX(cairo, child, style),
                    y = direction.postDrawTranslateY(cairo, child, style)
            )
        }
        cairo.restore()
    }

    // TODO: optimize, one pass is enough, or just put this function to Rectangle
    override fun layoutSize(cairo: Cairo): Size {
        if (children.isEmpty()) return Size(0, 0)
        return Size(
                direction.totalWidth(cairo, children),
                direction.totalHeight(cairo, children)
        )
    }
}
