package kogul.microdom

import kogul.drawing.*

sealed class Node {
    abstract val style: Style
    abstract val events: Events
    abstract fun draw(cairo: Cairo)
    var topLeft: Position? = null
    var cachedLayoutSize: Size? = null

    /** Returns what the size of the content would be, if not affected by width/height style attributes. */
    abstract fun defaultInnerSize(cairo: Cairo): Size

    fun fireEvent(event: Event) {
        events.map[event.type]?.forEach { it.invoke(event) }
    }

    /** Returns the actual size of content, after width/height style attributes, padding, and borders are taken into account. */
    fun innerSize(cairo: Cairo): Size {
        val default by lazy { defaultInnerSize(cairo) }
        val shrunk by lazy { layoutSize(cairo) - style.padding - style.margin - style.border?.width }
        return Size(
                if (style.width == null) default.width else shrunk.width,
                if (style.height == null) default.height else shrunk.height
        )
    }

    /** Returns the full size of the element, including the padding, border and margins. */
    fun layoutSize(cairo: Cairo): Size {
        val defaultSize by lazy { defaultInnerSize(cairo) + style.padding + style.border?.width }
        return Size(
                style.width ?: defaultSize.width,
                style.height ?: defaultSize.height
        ) + style.margin
    }

    open fun populateLayoutSize(cairo: Cairo) {
        cachedLayoutSize = layoutSize(cairo)
    }

    fun boundingRectangle(): Rectangle? = topLeft?.let {
        Rectangle(it, cachedLayoutSize!!)
    }
}

abstract class Container : Node() {
    abstract val children: List<Node>
    override fun populateLayoutSize(cairo: Cairo) {
        super.populateLayoutSize(cairo)
        children.forEach { it.populateLayoutSize(cairo) }
    }
}

abstract class Leaf : Node()

class Scene(val root: Node) {

    val flatNodes: List<Node> = if (root is Container) addToFlattenedList(mutableListOf(root), root) else listOf(root)
    var hasLayoutInfo = false

    init {
        root.topLeft = Position(0, 0)
    }

    private fun addToFlattenedList(list: MutableList<Node>, node: Container): List<Node> {
        list.addAll(node.children)
        node.children.forEach {
            if (it is Container) {
                addToFlattenedList(list, it)
            }
        }
        return list
    }

    fun draw(cairo: Cairo) {
        root.populateLayoutSize(cairo)
        root.draw(cairo)
        hasLayoutInfo = true
    }

    fun processEvent(event: Event) {
        when (event) {
            // TODO: only fire events that someone subscribed to
            // TODO: the same also applies a level up at window
            is MouseEvent -> { nodesAtPosition(event.position).forEach { it.fireEvent(event) }}
        }
    }

    fun nodesAtPosition(position: Position): List<Node> {
        if (!hasLayoutInfo) return emptyList()
        // TODO: obviously use some faster algorithm here later
        return flatNodes.filter { it.boundingRectangle()!!.contains(position) }
    }
}
