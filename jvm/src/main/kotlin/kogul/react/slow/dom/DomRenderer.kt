package kogul.react.slow.dom

import kogul.microdom.Node
import kogul.microdom.Style
import kogul.microdom.primitives.*
import kogul.react.slow.Element
import kogul.react.slow.ElementType
import kogul.react.slow.StringType
import kogul.react.slow.reconcile

class InvalidElementType : Exception()

class BoxProps(val style: Style = Style())
class TextProps(val text: String, val style: Style = Style())
class LayoutBoxProps(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        val style: Style = Style()
)

val BoxType = ElementType()
val TextType = ElementType()
val LayoutBoxType = ElementType()

fun constructDomNode(e: Element): Node =
    when(e.type) {
        BoxType -> Box(
                (e.props as BoxProps).style,
                e.children.map { constructDomNode(it) }
        )
        TextType -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
        StringType -> Text(e.props as String)
        LayoutBoxType -> {
            val props = e.props as LayoutBoxProps
            LayoutBox(props.direction, props.spacing, props.style, e.children.map { constructDomNode(it) })
        }
        else -> throw InvalidElementType()
    }

fun domRender(root: Element): Node {
    return constructDomNode(reconcile(root))
}
