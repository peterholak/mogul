@file:Suppress("UNUSED_PARAMETER")

package react

import drawing.Cairo
import kotlin.reflect.KClass

data class Props(
        val children: Children? = null
)
data class DivProps(val width: Int, val height: Int)
typealias Context = Any?
typealias State = Any
val emptyState = "EMPTY_STATE"

class Element(val type: KClass<out AnyComp>, key: String?, ref: String?, val props: Props) {
    override fun toString(): String {
        return "<${type.simpleName} props={${props.children}} />"
    }
}

sealed class AnyComponent(var props: Props, var context: Context, val updater: Updater, var state: State? = null)
typealias AnyComp = AnyComponent

abstract class RawHtmlComponent(props: Props, context: Context, updater: Updater) : AnyComponent(props, context, updater) {
    abstract fun draw(cairo: Cairo)
}

abstract class Component(props: Props, context: Context, updater: Updater) : AnyComponent(props, context, updater) {
    abstract fun render(): Element
}
typealias Children = List<Element>

fun createElement(type: KClass<out AnyComp>, config: Any? = null, children: Children? = null): Element {
    return Element(type, null, null, Props(children))
}
