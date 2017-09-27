@file:Suppress("unused")

package react

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor

class ShallowRenderer {
    var rendering = false
    var element: Element? = null
    var context: Context = null
    var instance: AnyComp? = null
    var rendered: Any? = null
    var newState: State? = null
    val updater = Updater(this)

    fun render(topElement: Element, context: Context = null): Any? {
        if (rendering) return null

        rendering = true
        element = topElement
        this.context = context

        if (instance != null) {
            updateClassComponent(element!!.props, context)
        }else{
            if (shouldConstruct(topElement.type)) {
                instance = topElement.type.primaryConstructor!!.call(
                        element!!.props,
                        context,
                        updater
                )
                mountClassComponent(element!!.props, context)
            }else{
                // this branch runs when element.type is not Component
                TODO(
                        "set rendered to element type TODO what no " +
                                "`new` in React code what, maybe `this` should " +
                                "be element there, serious wtf, it is retarded " +
                                "to structure code this way"
                )
            }
        }

        rendering = false
        return getRenderOutput()
    }

    fun updateClassComponent(props: Props, context: Context) {
        val component = instance!! as Component
        val oldProps = instance!!.props

        if (oldProps !== props) {
//            TODO("call componentWillReceiveProps")
        }

        val state = this.newState ?: component.state ?: emptyState
//        TODO("call shouldComponentUpdate and returns if it's false")

//        TODO("call componentWillUpdate")

        component.context = context
        component.props = props
        component.state = state

        rendered = component.render()
    }

    fun mountClassComponent(props: Props, context: Context) {
        val component = instance!! as Component

        component.context = context
        component.props = props
        component.state = component.state ?: emptyState
//        TODO("set updater")

//        TODO("call componentWillMount")

        rendered = component.render()
    }

    fun getRenderOutput() = rendered

    fun shouldConstruct(component: KClass<out AnyComp>) =
            component.isSubclassOf(Component::class)
}

typealias UpdaterCallback = (publicInstance: AnyComp) -> Unit
class Updater(val renderer: ShallowRenderer) {

    val isMounted
        get() = renderer.element != null

    fun enqueueForceUpdate(publicInstance: AnyComp, callback: UpdaterCallback? = null, callerName: String? = null) {
        renderer.render(renderer.element!!, renderer.context)
        callback?.invoke(publicInstance)
    }

    fun enqueueReplaceState(publicInstance: AnyComp, completeState: State, callback: UpdaterCallback? = null, callerName: String? = null) {
        renderer.newState = completeState
        renderer.render(renderer.element!!, renderer.context)
        callback?.invoke(publicInstance)
    }

    fun enqueueSetState(publicInstance: AnyComp, partialState: State, callback: UpdaterCallback? = null, callerName: String? = null) {
        // TODO: obviously setState only assigns the properties that are there
        // also it can be a function (and it is in flutter)
        renderer.newState = partialState

        renderer.render(renderer.element!!, renderer.context)
        callback?.invoke(publicInstance)
    }
}
