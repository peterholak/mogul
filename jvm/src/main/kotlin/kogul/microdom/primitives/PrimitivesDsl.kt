package kogul.microdom.primitives

import kogul.microdom.Node
import kogul.microdom.Scene
import kogul.microdom.Style

@DslMarker
annotation class MicroDomMarker

@MicroDomMarker
abstract class NodeBuilder {
    protected var innerStyle = Style()

    fun style(code: Style.() -> Unit) { code(innerStyle) }
    var style
        get() = innerStyle
        set(value) { innerStyle = value }

    abstract fun build(): Node
}

abstract class ContainerBuilder : NodeBuilder() {
    protected val children = mutableListOf<NodeBuilder>()

    fun box(code: BoxBuilder.() -> Unit) {
        val builder = BoxBuilder()
        code(builder)
        children.add(builder)
    }

    fun row(code: LayoutBoxBuilder.() -> Unit) {
        val builder = LayoutBoxBuilder(HorizontalDirection)
        code(builder)
        children.add(builder)
    }

    fun column(code: LayoutBoxBuilder.() -> Unit) {
        val builder = LayoutBoxBuilder(VerticalDirection)
        code(builder)
        children.add(builder)
    }

    fun text(text: String = "", code: TextBuilder.() -> Unit) {
        val builder = TextBuilder(text)
        code(builder)
        children.add(builder)
    }

    operator fun String.unaryMinus() {
        children.add(TextBuilder(this))
    }
}

class TextBuilder(private var text: String) : NodeBuilder() {
    override fun build() = Text(text, innerStyle)
    operator fun String.unaryMinus() {
        text += this
    }
}

class BoxBuilder : ContainerBuilder() {
    override fun build() = Box(innerStyle, children.map { it.build() })
}

class LayoutBoxBuilder(val direction: Direction) : ContainerBuilder() {
    var spacing = 0
    override fun build() = LayoutBox(direction, spacing, innerStyle, children.map { it.build() })
}

fun microDom(code: BoxBuilder.() -> Unit): Scene {
    val root = BoxBuilder()
    code(root)

    val node = root.build()
    // Use the top-level element as root, if there is only one
    if (node.children.size == 1) return Scene(node.children[0])
    return Scene(node)
}
