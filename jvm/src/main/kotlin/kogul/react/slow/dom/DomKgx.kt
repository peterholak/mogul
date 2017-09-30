@file:Suppress("unused")

package kogul.react.slow.dom

import kogul.microdom.Events
import kogul.microdom.Style
import kogul.microdom.events
import kogul.microdom.primitives.Direction
import kogul.microdom.primitives.HorizontalDirection
import kogul.microdom.style
import kogul.react.slow.Element
import kogul.react.slow.KgxBuilder

fun KgxBuilder.layoutBox(
        direction: Direction = HorizontalDirection,
        spacing: Int = 0,
        style: Style = Style(),
        builder: KgxBuilder.() -> Unit
) {
    val nested = KgxBuilder()
    builder(nested)
    children.add(Element(layoutBoxType, LayoutBoxProps(direction, spacing, style), nested.children))
}

fun KgxBuilder.box(style: Style = Style(), events: Events = Events(), builder: (KgxBuilder.() -> Unit)? = null) {
    val nested = KgxBuilder()
    builder?.invoke(nested)
    children.add(Element(boxType, BoxProps(style, events), nested.children))
}

fun KgxBuilder.text(text: String, style: Style = Style()) {
    children.add(Element(textType, TextProps(text, style)))
}

fun KgxBuilder.s(styleBuilder: Style.() -> Unit) = style(styleBuilder)
fun KgxBuilder.e(eventBuilder: Events.() -> Unit) = events(eventBuilder)
