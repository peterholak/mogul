package kogul.react.slow

import kotlin.reflect.KClass

typealias ElementType = KClass<*>
abstract class Component<PropTypes> internal constructor() {

    private var hackyProps: PropTypes? = null
    private var hackyChildren: List<Element>? = null
    val props by lazy { hackyProps!! }
    val children by lazy { hackyChildren!! }

    internal fun createInstance(props: PropTypes?, children: List<Element>) {
        hackyProps = props
        hackyChildren = children
    }

    abstract fun render(): Element
}
data class Element(val type: ElementType, val props: Any, val children: List<Element> = emptyList())
