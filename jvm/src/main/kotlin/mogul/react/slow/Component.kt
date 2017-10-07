package mogul.react.slow

interface Updater {
    fun update()
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

interface Copyable {
    fun copy(): Copyable
}

typealias StateMap = MutableMap<String, Any?>
// Somewhat of an ugly way of dealing with this without using reflection
abstract class State(private val create: () -> State) : Copyable {
    protected val map: StateMap = mutableMapOf()
    override fun copy(): State {
        val newState = create()
        newState.map.putAll(map)
        return newState
    }
}

abstract class StatefulComponent<out PropTypes, StateType : Copyable> : Component<PropTypes>() {
    val state: StateType
        get() = currentState ?: initialState

    abstract val initialState: StateType

    private var currentState: StateType? = null
    // To be able to actually use this, I need an actual reconciler that doesn't just throw everything away
    private var newState: StateType? = null

    fun setState(mutation: StateType.() -> Unit) {
        if (newState == null) {
            @Suppress("UNCHECKED_CAST")
            newState = state.copy() as StateType
        }
        mutation(newState!!)
        // TODO: why exactly can't I just put the new state in there right here (so it'd be synchronous)?
        // TODO: batch updates?
        updater.update()
    }

    internal fun updateToNewState() {
        newState?.let { currentState = it }
        newState = null
    }
}

typealias ComponentConstructor = () -> Component<Any>
val stringType = ElementType("string")

// This is because Kotlin Native currently doesn't support much reflection, otherwise Component::class could be used.
class ElementType(val name: String, val constructComponent: ComponentConstructor? = null) {
    override fun toString(): String {
        return name + if (constructComponent != null) " (C)" else ""
    }
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
class Remove(val element: InstantiatedElement): Change()
class Modify(val oldProps: Any) : Change()
