package mogul.react.slow

interface Reconciler {
    fun reconcile(root: Element, oldTree: InstantiatedElement?, updater: Updater): InstantiatedElement
}

// Not useful in practice, only for demonstration/explanation purposes.
// Never modifies any existing component instance or DOM element,
// just re-creates everything from scratch on each update.
object RemoveEverythingReconciler : Reconciler {
    override fun reconcile(root: Element, oldTree: InstantiatedElement?, updater: Updater): InstantiatedElement {
        return when {
            root.type.constructComponent != null -> {
                @Suppress("UNCHECKED_CAST")
                val instance = root.type.constructComponent.invoke()
                instance.createInstance(root.props, root.children, updater)
                return InstantiatedElement(
                        type = root.type,
                        props = root.props,
                        children = listOf(reconcile(instance.render(), null, updater)),
                        instance = instance
                )
            }
            else -> InstantiatedElement(
                    type = root.type,
                    props = root.props,
                    children = root.children.map { reconcile(it, null, updater) },
                    instance = Unit
            )
        }
    }
}

// This should have pretty much the same behavior as described in https://reactjs.org/docs/reconciliation.html
object ReactReconciler : Reconciler {
    override fun reconcile(root: Element, oldTree: InstantiatedElement?, updater: Updater): InstantiatedElement {
        return when {

            // Same type, a component, already existing instance -> keep the instance, just change props/subtree
            root.type == oldTree?.type && oldTree.instance is Component<*> ->
                reconcileExistingComponent(root, oldTree, oldTree.instance, updater)

            // It's a component, but there is either no instance yet, or it's of a different type -> create a new instance
            root.type.constructComponent != null && root.type != oldTree?.type ->
                // TODO: unmount old component (if oldTree != null)
                reconcileNewComponent(root, root.type.constructComponent, updater)

            // They are of the same type, already existing instance, but they are not components (DOM elements, etc.)
            root.type == oldTree?.type && root.type.constructComponent == null ->
                reconcileExistingPlatformElement(root, oldTree, updater)

            // Different type (and again, TODO: unmount any existing old one if it's there)
            else -> reconcileNewPlatformElement(root, updater)
        }
    }

    private fun reconcileExistingComponent(
        root: Element,
        oldTree: InstantiatedElement,
        oldTreeInstance: Component<*>,
        updater: Updater
    ): InstantiatedElement {
        // TODO: lifecycle willReceiveProps etc.
        oldTreeInstance.updateProps(root.props)
        if (oldTreeInstance is StatefulComponent<*, *>) {
            oldTreeInstance.updateToNewState()
        }
        val newRender = oldTreeInstance.render()
        return InstantiatedElement(
                type = root.type,
                props = root.props,
                children = listOf(reconcile(newRender, oldTree.children.single(), updater)),
                instance = oldTreeInstance
        )
    }

    private fun reconcileNewComponent(root: Element, rootTypeConstruct: ComponentConstructor, updater: Updater): InstantiatedElement {
        val newInstance = rootTypeConstruct.invoke()
        newInstance.createInstance(root.props, root.children, updater)
        val newRender = newInstance.render()
        return InstantiatedElement(
                type = root.type,
                props = root.props,
                children = listOf(reconcile(newRender, null, updater)),
                instance = newInstance
        )
    }

    private fun reconcileExistingPlatformElement(root: Element, oldTree: InstantiatedElement, updater: Updater): InstantiatedElement {
        // For now let's not reuse the existing instance, this will be changed later
        return InstantiatedElement(
                type = root.type,
                props = root.props,
                children = root.children.mapIndexed { index, item ->
                    // TODO: Here the `key` and shit will come into play, for now just this:
                    reconcile(item, oldTree.children.getOrNull(index), updater)
                },
                instance = Unit
        )
    }

    private fun reconcileNewPlatformElement(root: Element, updater: Updater): InstantiatedElement {
        return InstantiatedElement(
                type = root.type,
                props = root.props,
                children = root.children.map { reconcile(it, null, updater) },
                instance = Unit
        )
    }
}