package mogul.microdom

import mogul.microdom.ObservableList.Change.*
import mogul.platform.*

sealed class Node {
    var parent: Container? = null; internal set

    abstract var style: Style
    open val hoverStyle: Style? = null
    open val mouseDownStyle: Style? = null
    abstract val events: Events
    abstract fun draw(cairo: Cairo)
    var topLeft: Position? = null
    var cachedLayoutSize: Size? = null
    var state = NodeState()

    /** Returns what the size of the content would be, if not affected by width/height style attributes. */
    abstract fun defaultInnerSize(cairo: Cairo): Size

    fun effectiveStyle(state: NodeState): Style {
        var result = style
        if (state.hover && hoverStyle != null) result += hoverStyle!!
        if (state.mouseDown && mouseDownStyle != null) result += mouseDownStyle!!
        return result
    }

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

    fun replaceWith(newChild: Node) {
        if (parent !is Container) {
            throw UnsupportedOperationException("replaceWith cannot be called on root node! (parent of node must be a Container).")
        }else{
            (parent as Container).replaceChild(this, newChild)
        }
    }
}

// TODO: this doesn't really notify on all operations yet, so that needs to be fixed
class ObservableList<T>(private val list: MutableList<T> = mutableListOf()): MutableList<T> by list {

    constructor(vararg children: T) : this(children.toMutableList())

    val listeners = mutableListOf<(change: Change<T>) -> Unit>()
    private fun notifyListeners(change: Change<T>) { listeners.forEach { it.invoke(change) }}

    sealed class Change<T> {
        class Added<T>(val elements: Collection<T>) : Change<T>()
        class Removed<T>(val elements: Collection<T>) : Change<T>()
    }

    override fun add(element: T) =
        list.add(element).also { notifyListeners(Added(listOf(element))) }

    override fun add(index: Int, element: T) =
        list.add(index, element).also { notifyListeners(Added(listOf(element))) }

    override fun addAll(elements: Collection<T>) =
        list.addAll(elements).also { notifyListeners(Added(elements)) }

    override fun addAll(index: Int, elements: Collection<T>) =
        list.addAll(index, elements).also { notifyListeners(Added(elements)) }

    override fun remove(element: T) =
        list.remove(element).also { notifyListeners(Removed(listOf(element))) }

    override fun removeAt(index: Int) =
        list.removeAt(index).also { notifyListeners(Removed(listOf(it))) }

    override fun removeAll(elements: Collection<T>) =
        list.removeAll(elements).also { notifyListeners(Removed(elements)) }

    // This could be pretty inefficient and maybe pointless (right now its only used for setting parent to null)
    override fun clear() = list.also { notifyListeners(Removed(it)) }.clear()

    // Maybe the Removed should be called as well, or it should be a different Change type, but for now I don't need it
    override fun set(index: Int, element: T) =
        list.set(index, element).also { notifyListeners(Removed(listOf(it))) }.also{ notifyListeners(Added(listOf(element))) }
}

class NodeNotFoundException : Exception()
abstract class Container(initialChildren: List<Node>) : Node() {

    val children: ObservableList<Node> = ObservableList()

    init {
        children.listeners.add { change ->
            when (change) {
                is Added -> change.elements.forEach {
                    if (it.parent != null) {
                        // TODO Not thread safe
                        it.parent!!.children.remove(it)
                    }
                    it.parent = this
                }
                // Is there any point to this?
                is Removed -> change.elements.forEach { it.parent = null }
            }
        }
        children.addAll(initialChildren)
    }

    override fun populateLayoutSize(cairo: Cairo) {
        super.populateLayoutSize(cairo)
        children.forEach { it.populateLayoutSize(cairo) }
    }

    internal fun replaceChild(old: Node, new: Node) {
        val position = children.indexOf(old)
        if (position == -1) throw NodeNotFoundException()
        children[position] = new
    }
}

abstract class Leaf : Node()

class Scene(root: Node) {

    var root: Node; private set
    // Obviously a better notification system will be used later (or a different mechanism altogether)
    internal var onInvalidated: (() -> Unit)? = null

    init {
        this.root = root
    }

    val flatNodes: MutableList<Node> =
            if (root is Container)
                addToFlattenedList(mutableListOf(root), root)
            else
                mutableListOf(root)
    var hasLayoutInfo = false

    init {
        root.topLeft = Position(0, 0)
    }

    fun replaceRoot(newRoot: Node) {
        root = newRoot
        invalidate()
    }

    fun invalidate() {
        // TODO: this will really need thread safety later
        hasLayoutInfo = false
        flatNodes.clear()
        if (root is Container)
            addToFlattenedList(flatNodes, root as Container)
        else
            flatNodes.add(root)
        onInvalidated?.invoke()
    }

    private fun addToFlattenedList(list: MutableList<Node>, node: Container): MutableList<Node> {
        list.addAll(node.children)
        node.children.forEach {
            if (it is Container) {
                addToFlattenedList(list, it)
            }
        }
        return list
    }

    fun draw(cairo: Cairo) {
        if (!hasLayoutInfo) {
            root.populateLayoutSize(cairo)
        }
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

/** This could be optimized by having the state represented with bitfields */
data class NodeState(
    val hover: Boolean = false,
    val mouseDown: Boolean = false
)