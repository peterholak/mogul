package mogul.react.slow

import kotlin.reflect.full.*

interface Updater {
    fun update()
}

abstract class Component<out PropTypes> {

    private var hackyProps: PropTypes? = null
    private lateinit var hackyChildren: List<Element>
    protected lateinit var updater: Updater
    val props by lazy { hackyProps!! }
    val children by lazy { hackyChildren }

    // This hack is here to hide the implementation details from users who implement components
    // - if only Kotlin had some construct to easily auto-inherit the default constructor...
    internal fun createInstance(props: Any, children: List<Element>, updater: Updater) {
        @Suppress("UNCHECKED_CAST")
        hackyProps = props as PropTypes
        hackyChildren = children
        this.updater = updater
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

abstract class StatefulComponent<out PropTypes, StateType : Any> : Component<PropTypes>() {
    abstract var state: StateType
    // To be able to actually use this, I need an actual reconciler that doesn't just throw everything away
    internal var newState: StateType? = null

    fun setState(mutation: StateType.() -> Unit) {
        @Suppress("UNCHECKED_CAST")
        newState = copyState(state) as StateType
        mutation(newState!!)
        updater.update()
    }
}

typealias ComponentConstructor = () -> Component<Any>
val stringType = ElementType()

// This is because Kotlin Native currently doesn't support much reflection, otherwise Component::class could be used.
class ElementType(val constructComponent: ComponentConstructor? = null)
data class Element(val type: ElementType, val props: Any, val children: List<Element> = emptyList())
