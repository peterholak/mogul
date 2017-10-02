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

fun constructDomNode(e: Element): Node =
    when(e.type) {
        boxType -> {
            val props = e.props as BoxProps
            Box(props.style, props.events, e.children.mapTo(ObservableList()) { constructDomNode(it) })
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
                    e.children.mapTo(ObservableList()) { constructDomNode(it) }
            )
        }
        else -> throw InvalidElementType()
    }

class DomUpdater(val root: Element, val scene: Scene, val triggerRedraw: () -> Unit) : Updater {
    override fun update() {
        scene.replaceRoot(constructDomNode(reconcile(root, this)))
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
