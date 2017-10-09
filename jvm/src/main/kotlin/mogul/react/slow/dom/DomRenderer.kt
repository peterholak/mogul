package mogul.react.slow.dom

import mogul.microdom.Events
import mogul.microdom.Node
import mogul.microdom.Scene
import mogul.microdom.Style
import mogul.microdom.primitives.*
import mogul.react.slow.*

data class BoxProps(val style: Style = Style(), val events: Events = Events())
data class TextProps(val text: String, val style: Style = Style(), val events: Events = Events())
data class LayoutBoxProps(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        val style: Style = Style(),
        val events: Events = Events()
)

val boxType = ElementType("dom.box")
val textType = ElementType("dom.text")
val layoutBoxType = ElementType("dom.layoutBox")

// For now, the `instance` field is not used and this always creates new dom nodes.
fun constructDomNode(e: InstantiatedElement): Node {
    return when (e.type) {
        boxType -> {
            val props = e.props as BoxProps
            Box(props.style, props.events, e.children.map { constructDomNode(it) })
        }
        textType -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
        stringType -> Text(e.props as String)
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
        // This happens when `e` is the element of a Component in the VDOM
        // Let's just skip it here and move on to its first child
        // The reason the component's element itself stays in the VDOM is for later diffing...
        // TODO: checks for when it's not a component
        else -> constructDomNode(e.children.single())
    }
}


fun updateDom(scene: Scene, e: InstantiatedElement, toRemove: List<Remove>) {
    scene.replaceRoot(constructDomNode(e))
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


class DomUpdater(val root: Element, val scene: Scene) : Updater {
    var oldTree: InstantiatedElement? = null

    override fun queueUpdate() {
        val toRemove = mutableListOf<Remove>()
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
