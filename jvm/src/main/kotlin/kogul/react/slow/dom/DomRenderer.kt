package kogul.react.slow.dom

import kogul.microdom.Node
import kogul.microdom.Style
import kogul.microdom.primitives.*
import kogul.react.slow.Element
import kogul.react.slow.reconcile
import kotlin.reflect.KClass

class InvalidElementType(val type: KClass<*>) : Exception()

class BoxProps(val style: Style = Style())
class TextProps(val text: String, val style: Style = Style())
class LayoutBoxProps(
        val direction: Direction = HorizontalDirection,
        val spacing: Int = 0,
        val style: Style = Style()
)

fun constructDomNode(e: Element): Node =
    when(e.type) {
        Box::class -> Box(
                (e.props as BoxProps).style,
                e.children.map { constructDomNode(it) }
        )
        Text::class -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
        String::class -> Text(e.props as String)
        LayoutBox::class -> {
            val props = e.props as LayoutBoxProps
            LayoutBox(props.direction, props.spacing, props.style, e.children.map { constructDomNode(it) })
        }
        else -> throw InvalidElementType(e.type)
    }

fun domRender(root: Element): Node {
    return constructDomNode(reconcile(root))
}
