package kogul.react.slow

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

fun hasComponentType(element: Element) = element.type.isSubclassOf(Component::class)

fun reconcile(root: Element): Element {
    return when {
        hasComponentType(root) -> {
            @Suppress("UNCHECKED_CAST")
            val instance = (root.type.createInstance() as Component<in Any?>)
            // TODO: props really shouldn't be nullable
            instance.createInstance(root.props, root.children)
            reconcile(instance.render())
        }
        else -> Element(root.type, root.props, root.children.map { reconcile(it) })
    }
}
