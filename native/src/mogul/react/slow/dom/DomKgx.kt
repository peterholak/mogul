@file:Suppress("unused")

package mogul.react.slow.dom

import mogul.microdom.Events
import mogul.microdom.Style
import mogul.microdom.events
import mogul.microdom.primitives.Direction
import mogul.microdom.primitives.HorizontalDirection
import mogul.microdom.style
import mogul.react.slow.Element
import mogul.react.slow.KgxBuilder

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
