package mogul.react.slow.dom

import mogul.microdom.*
import mogul.microdom.primitives.*
import mogul.react.slow.*

class InvalidElementType : Exception()

class BoxProps(val style: Style = Style(), val events: Events = Events())
class TextProps(val text: String, val style: Style = Style(), val events: Events = Events())
class LayoutBoxProps(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        val style: Style = Style(),
        val events: Events = Events()
)

val boxType = ElementType()
val textType = ElementType()
val layoutBoxType = ElementType()

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
        else -> constructDomNode(e.children.single())
    }
}

class DomUpdater(val root: Element, val scene: Scene, val triggerRedraw: () -> Unit) : Updater {
    var oldTree: InstantiatedElement? = null

    override fun update() {
        val tree = ReactReconciler.reconcile(root, oldTree, this)
        scene.replaceRoot(constructDomNode(tree))
        oldTree = tree
        triggerRedraw()
    }
}

// This clearly needs more features in the DOM itself before proceeding...
fun domRender(root: Element, triggerRedraw: () -> Unit): Scene {
    val scene = Scene(Box()) // lol
    val newUpdater = DomUpdater(root, scene, triggerRedraw)
    newUpdater.update()
    return scene
}
