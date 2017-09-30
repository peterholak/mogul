package kogul.react.slow

class KgxBuilder {
    val children = mutableListOf<Element>()
    operator fun String.unaryMinus() = children.add(Element(String::class, this))
}

fun kgx(code: KgxBuilder.() -> Unit): Element {
    val builder = KgxBuilder()
    code(builder)
    return builder.children[0] // TODO: checks
}
