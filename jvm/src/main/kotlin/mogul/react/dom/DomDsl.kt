@file:Suppress("unused")

package mogul.react.dom

import mogul.microdom.Events
import mogul.microdom.Style
import mogul.microdom.primitives.Direction
import mogul.microdom.primitives.HorizontalDirection
import mogul.react.Element
import mogul.react.GuiBuilder

fun GuiBuilder.layoutBox(
        direction: Direction = HorizontalDirection,
        spacing: Int = 0,
        style: Style = Style(),
        builder: GuiBuilder.() -> Unit
) {
    val nested = GuiBuilder()
    builder(nested)
    children.add(Element(layoutBoxType, LayoutBoxProps(direction, spacing, style), nested.children))
}

fun GuiBuilder.box(
    style: Style = Style(),
    hoverStyle: Style? = null,
    mouseDownStyle: Style? = null,
    events: Events = Events(),
    builder: (GuiBuilder.() -> Unit)? = null
) {
    val nested = GuiBuilder()
    builder?.invoke(nested)
    children.add(Element(boxType, BoxProps(style, hoverStyle, mouseDownStyle, events), nested.children))
}

fun GuiBuilder.text(text: String, style: Style = Style()) {
    children.add(Element(textType, TextProps(text, style)))
}
