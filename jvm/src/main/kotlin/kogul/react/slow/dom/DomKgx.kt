@file:Suppress("unused")

package kogul.react.slow.dom

import kogul.microdom.Style
import kogul.microdom.primitives.*
import kogul.microdom.style
import kogul.react.slow.Element
import kogul.react.slow.KgxBuilder

fun KgxBuilder.layoutBox(
        direction: Direction = HorizontalDirection,
        spacing: Int = 0,
        builder: KgxBuilder.() -> Unit
) {
    val nested = KgxBuilder()
    builder(nested)
    children.add(Element(LayoutBoxType, LayoutBoxProps(direction, spacing), nested.children))
}

fun KgxBuilder.box(style: Style = Style(), builder: (KgxBuilder.() -> Unit)? = null) {
    val nested = KgxBuilder()
    builder?.invoke(nested)
    children.add(Element(BoxType, BoxProps(style), nested.children))
}

fun KgxBuilder.text(text: String, style: Style = Style()) {
    children.add(Element(TextType, TextProps(text, style)))
}

fun KgxBuilder.s(styleBuilder: Style.() -> Unit) = style(styleBuilder)
