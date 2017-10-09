package mogul.react.slow

import mogul.microdom.primitives.MicroDomMarker
import mogul.react.slow.dom.TextProps
import mogul.react.slow.dom.textType

@MicroDomMarker
class KgxBuilder {
    val children = mutableListOf<Element>()
    // This could be special cased and optimized (`stringType`, but currently not used)
    operator fun String.unaryMinus() = children.add(Element(textType, TextProps(this)))
}

fun kgx(code: KgxBuilder.() -> Unit): Element {
    val builder = KgxBuilder()
    code(builder)
    return builder.children.single()
}
