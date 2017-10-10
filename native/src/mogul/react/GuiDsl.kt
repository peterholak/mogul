package mogul.react

import mogul.microdom.primitives.MicroDomMarker
import mogul.react.dom.TextProps
import mogul.react.dom.textType

@MicroDomMarker
class GuiBuilder {
    val children = mutableListOf<Element>()
    // This could be special cased and optimized (`stringType`, but currently not used)
    operator fun String.unaryMinus() = children.add(Element(textType, TextProps(this)))
}

fun gui(code: GuiBuilder.() -> Unit): Element {
    val builder = GuiBuilder()
    code(builder)
    return builder.children.single()
}
