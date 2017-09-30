package kogul.react.slow

class KgxBuilder {
    val children = mutableListOf<Element>()
    operator fun String.unaryMinus() = children.add(Element(StringType, this))
}

fun kgx(code: KgxBuilder.() -> Unit): Element {
    val builder = KgxBuilder()
    code(builder)
    return builder.children[0] // TODO: checks
}
