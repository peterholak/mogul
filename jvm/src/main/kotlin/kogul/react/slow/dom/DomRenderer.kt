package kogul.react.slow.dom

import kogul.microdom.Events
import kogul.microdom.Node
import kogul.microdom.Style
import kogul.microdom.primitives.*
import kogul.react.slow.*

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
            Box(props.style, props.events, e.children.map { constructDomNode(it) })
        }
        textType -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
        stringType -> Text(e.props as String)
        layoutBoxType -> {
            val props = e.props as LayoutBoxProps
            LayoutBox(props.direction, props.spacing, props.style, props.events, e.children.map { constructDomNode(it) })
        }
        else -> throw InvalidElementType()
    }

fun domRender(root: Element): Node {
    return constructDomNode(reconcile(root))
}
