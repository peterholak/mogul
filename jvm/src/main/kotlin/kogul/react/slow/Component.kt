package kogul.react.slow

import kotlin.reflect.full.*

abstract class Component<out PropTypes> {

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

fun copyState(state: Any): Any {
    // This is hacky as fuck, I need to find a better solution for this
    // Some things could be cached for better performance, but it still won't work in native...
    // In general, it's probably better to give up on this altogether and use a different mechanism for state
    val values = state::class.primaryConstructor!!.valueParameters.associate { parameter ->
        // Slow as fuck, but it doesn't matter at this stage
        val property = state::class.declaredMemberProperties.find { it.name == parameter.name }!!
        parameter to property.getter.call(state)
    }
    return state::class.primaryConstructor!!.callBy(values)
}

abstract class StatefulComponent<out PropTypes, out StateType : Any> : Component<PropTypes>() {
    abstract val state: StateType

    fun setState(mutation: StateType.() -> Unit) {
        @Suppress("UNCHECKED_CAST")
        mutation(copyState(state) as StateType)
    }
}

typealias ComponentConstructor = () -> Component<Any>
val stringType = ElementType()

// This is because Kotlin Native currently doesn't support much reflection, otherwise Component::class could be used.
class ElementType(val constructComponent: ComponentConstructor? = null)
data class Element(val type: ElementType, val props: Any, val children: List<Element> = emptyList())
