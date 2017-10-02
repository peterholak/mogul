package mogul.react.slow

import mogul.microdom.primitives.MicroDomMarker

@MicroDomMarker
class KgxBuilder {
    val children = mutableListOf<Element>()
    operator fun String.unaryMinus() = children.add(Element(stringType, this))
}

fun kgx(code: KgxBuilder.() -> Unit): Element {
    val builder = KgxBuilder()
    code(builder)
    return builder.children[0] // TODO: checks
}
