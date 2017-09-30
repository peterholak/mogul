package kogul.react.slow

abstract class Component<PropTypes> {

    private var hackyProps: PropTypes? = null
    private var hackyChildren: List<Element>? = null
    val props by lazy { hackyProps!! }
    val children by lazy { hackyChildren!! }

    internal fun createInstance(props: Any, children: List<Element>) {
        @Suppress("UNCHECKED_CAST")
        hackyProps = props as PropTypes
        hackyChildren = children
    }

    abstract fun render(): Element
}

typealias ComponentConstructor = () -> Component<out Any>
val stringType = ElementType()

// This is because Kotlin Native currently doesn't support much reflection, otherwise Component::class could be used.
class ElementType(val constructComponent: ComponentConstructor? = null)
data class Element(val type: ElementType, val props: Any, val children: List<Element> = emptyList())
