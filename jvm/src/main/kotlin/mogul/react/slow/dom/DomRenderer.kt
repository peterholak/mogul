package mogul.react.slow.dom

import mogul.microdom.*
import mogul.microdom.primitives.*
import mogul.react.slow.*

interface NodeProps {
    val style: Style
}
data class BoxProps(
    override val style: Style = Style(),
    val hoverStyle: Style? = null,
    val mouseDownStyle: Style? = null,
    val events: Events = Events()
) : NodeProps
data class TextProps(val text: String, override val style: Style = Style(), val events: Events = Events()) : NodeProps
data class LayoutBoxProps(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        override val style: Style = Style(),
        val events: Events = Events()
) : NodeProps

val boxType = ElementType("dom.box")
val textType = ElementType("dom.text")
val layoutBoxType = ElementType("dom.layoutBox")

class InvalidElementType(element: InstantiatedElement) : Exception()

fun constructDomNode(e: InstantiatedElement): Node {
    if (e.type.isComponent()) {
        return constructDomNode(e.children.single())
    }

    val result: Node = when (e.type) {
        boxType -> {
            val props = e.props as BoxProps
            Box(props.style, props.hoverStyle, props.mouseDownStyle, props.events, e.children.map { constructDomNode(it) })
        }
        textType -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
//        stringType -> Text(e.props as String)
        layoutBoxType -> {
            val props = e.props as LayoutBoxProps
            LayoutBox(
                    props.direction,
                    props.spacing,
                    props.style,
                    props.events,
                    e.children.map { constructDomNode(it) }
            )
        }
        else -> throw InvalidElementType(e)
    }
    return e.populateDomInstance(result)
}


fun updateDom(scene: Scene, root: InstantiatedElement, toRemove: List<InstantiatedElement>) {

    toRemove.forEach {
        val node = it.castDomInstance<Node>()
        node.parent?.children?.remove(node)
    }

    when (root.change) {
        is Add ->
            error("Bad output from reconciler: cannot add new root.")

        is Replace ->
            scene.replaceRoot(constructDomNode(root))

        is Modify -> {
            val rootNode = root.castDomInstance<Container>()
            updateNodeProps(rootNode, root.change.oldProps as NodeProps, root.props as NodeProps)
            root.children.forEach {
                updateDomElement(rootNode, it)
            }
            scene.invalidate()
        }
    }
}

/**
 * Updates the props and children of an existing DOM element, or creates a new DOM element if it didn't exist yet.
 * TODO: the forceReplace bullshit could be done in a cleaner way
 */
private fun updateDomElement(parent: Container, e: InstantiatedElement, forceReplace: Boolean = false) {
    // Skip over component elements
    if (e.type.isComponent()) {
        if (e.change is Replace) {
            parent.replaceChild(
                    (e.change.oldComponent!!.children.single().instance as Later<Node>).value!!,
                    constructDomNode(e.children.single())
            )
            return
        }else {
            return updateDomElement(parent, e.children.single())
        }
    }

    when (e.change) {
        is Add ->
            parent.children.add(constructDomNode(e))

        is Replace ->
            parent.replaceChild((e.change.oldInstance as Later<*>).value as Node, constructDomNode(e))

        is Modify -> {
            val instance = e.castDomInstance<Node>()
            updateNodeProps(instance, e.change.oldProps as NodeProps, e.props as NodeProps)
            if (instance is Container) {
                e.children.forEach {
                    updateDomElement(instance, it)
                }
            }
        }
    }
}

private fun updateNodeProps(node: Node, oldProps: NodeProps, newProps: NodeProps) {
    node.style = newProps.style

    // TODO: all the other props
    when(node) {
        is Text -> {
            val props = newProps as TextProps
            node.text = props.text
        }
    }
}


class DomUpdater(val root: Element, val scene: Scene) : Updater {
    var oldTree: InstantiatedElement? = null

    override fun queueUpdate() {
        doUpdate()
    }

    fun doUpdate() {
        val toRemove = mutableListOf<InstantiatedElement>()
        val tree = ReactReconciler.reconcile(root, oldTree, ReconcileRunArguments(this, toRemove))
        oldTree = tree
        scene.replaceRoot(constructDomNode(tree))
    }
}

// This clearly needs more features in the DOM itself before proceeding...
fun domRender(root: Element): Scene {
    val scene = Scene(Box()) // lol
    DomUpdater(root, scene).queueUpdate()
    return scene
}

inline fun <reified T> InstantiatedElement.castDomInstance() =
    (instance as Later<*>).value as T

inline fun <reified T> InstantiatedElement.populateDomInstance(data: T): T {
    @Suppress("UNCHECKED_CAST")
    (instance as Later<T?>).value = data
    return data
}
