package mogul.react.slow

interface Updater {
    fun queueUpdate()
}

abstract class Component<out PropTypes> {

    private var hackyProps: PropTypes? = null
    private lateinit var hackyChildren: List<Element>
    protected lateinit var updater: Updater
    val props
        get() = hackyProps!!
    val children by lazy { hackyChildren }

    // This hack is here to hide the implementation details from users who implement components
    // - if only Kotlin had some construct to easily auto-inherit the default constructor...
    internal fun createInstance(props: Any, children: List<Element>, updater: Updater) {
        @Suppress("UNCHECKED_CAST")
        hackyProps = props as PropTypes
        hackyChildren = children
        this.updater = updater
    }

    internal fun updateProps(newProps: Any) {
        // TODO: this should really only update the props that have changed (maybe to preserve some identity semantics?)
        @Suppress("UNCHECKED_CAST")
        hackyProps = newProps as PropTypes
    }

    abstract fun render(): Element
}

abstract class StatefulComponent<out PropTypes, StateType> : Component<PropTypes>() {
    val state: StateType
        get() = currentState ?: initialState

    abstract val initialState: StateType

    private var currentState: StateType? = null

    fun setState(newState: StateType) {
        currentState = newState
        updater.queueUpdate()
    }
}

typealias ComponentConstructor = () -> Component<Any>
val stringType = ElementType("string")

// This is because Kotlin Native currently doesn't support much reflection, otherwise Component::class could be used.
class ElementType(val name: String, val constructComponent: ComponentConstructor? = null) {
    override fun toString(): String {
        return name + if (constructComponent != null) " (C)" else ""
    }

    fun isComponent() = constructComponent != null
}

data class Element(
    val type: ElementType,
    val props: Any,
    val children: List<Element> = emptyList()
)

// Something like this should also be available for DOM-backed elements
class InstantiatedElement(
    val type: ElementType,
    val props: Any,
    val children: List<InstantiatedElement>,
    val instance: Any,
    val change: Change?
)

sealed class Change
class Add : Change()
class Modify(val oldProps: Any) : Change()
// TODO: clean this up
class Replace(val oldInstance: Any, val oldComponent: InstantiatedElement? = null) : Change()
