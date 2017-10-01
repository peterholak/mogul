package kogul.react.slow

fun reconcile(root: Element, updater: Updater): Element {
    return when {
        root.type.constructComponent != null -> {
            @Suppress("UNCHECKED_CAST")
            val instance = root.type.constructComponent.invoke()
            // TODO: props really shouldn't be nullable
            instance.createInstance(root.props, root.children, updater)
            reconcile(instance.render(), updater)
        }
        else -> Element(root.type, root.props, root.children.map { reconcile(it, updater) })
    }
}
