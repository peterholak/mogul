package mogul.react

import mogul.react.injection.ServiceContainer

/** Grouped arguments that don't change during an entire run of a reconcile operation, but are just passed down */
class ReconcileRunArguments(val updater: Updater, val container: ServiceContainer, val toRemove: MutableList<InstantiatedElement>)

/** This should have pretty much the same behavior as described in https://reactjs.org/docs/reconciliation.html */
fun reconcile(root: Element, oldTree: InstantiatedElement?, args: ReconcileRunArguments): InstantiatedElement {
    return when {

        // Same type, a component, already existing instance -> keep the instance, just change props/subtree
        root.type == oldTree?.type && oldTree.instance is Component<*> ->
            reconcileExistingComponent(root, oldTree, oldTree.instance, args)

        // It's a component, but there is either no instance yet, or it's of a different type -> create a new instance
        root.type.constructComponent != null && root.type != oldTree?.type -> {
            reconcileNewComponent(root, oldTree, root.type.constructComponent, args)
        }

        // They are of the same type, already existing instance, but they are not components
        root.type == oldTree?.type && root.type.constructComponent == null ->
            reconcileExistingPlatformElement(root, oldTree, args)

        // Different type
        else -> {
            reconcileNewPlatformElement(root, oldTree, args)
        }
    }
}

private fun reconcileExistingComponent(
    root: Element,
    oldTree: InstantiatedElement,
    oldTreeInstance: Component<*>,
    args: ReconcileRunArguments
): InstantiatedElement {
    // TODO: lifecycle willReceiveProps, should update, etc.
    oldTreeInstance.updateProps(root.props)
    val reconciledChildren = listOf(reconcile(oldTreeInstance.render(), oldTree.children.single(), args))
    val childrenChanged = (reconciledChildren.any { it.change != null })
    return InstantiatedElement(
            type = root.type,
            props = root.props,
            children = reconciledChildren,
            instance = oldTreeInstance,
            change = if (childrenChanged || oldTree.props != root.props) Modify(oldTree.props) else null
    )
}

private fun reconcileNewComponent(root: Element, oldTree: InstantiatedElement?, rootTypeConstruct: ComponentConstructor, args: ReconcileRunArguments): InstantiatedElement {
    val newInstance = rootTypeConstruct.invoke(args.container)
    newInstance.createInstance(root.props, root.children, args.updater)
    val newRender = newInstance.render()
    return InstantiatedElement(
            type = root.type,
            props = root.props,
            children = listOf(reconcile(newRender, null, args)),
            instance = newInstance,
            change = if (oldTree == null) Add() else Replace(oldTree.instance, oldTree)
    )
}

private fun reconcileExistingPlatformElement(root: Element, oldTree: InstantiatedElement, args: ReconcileRunArguments): InstantiatedElement {
    val childrenRemoved = root.children.size < oldTree.children.size
    if (childrenRemoved) {
        args.toRemove.addAll(
                oldTree.children.subList(root.children.size, oldTree.children.size)
        )
    }

    val reconciledChildren = root.children.mapIndexed { index, item ->
        // TODO: Here the `key` and shit will come into play, for now just this:
        reconcile(item, oldTree.children.getOrNull(index), args)
    }
    val childrenChanged = childrenRemoved || reconciledChildren.any { it.change != null }
    // TODO: if nothing changed, just return oldTree directly?
    return InstantiatedElement(
            type = root.type,
            props = root.props,
            children = reconciledChildren,
            instance = oldTree.instance,
            change = if (childrenChanged || root.props != oldTree.props) Modify(oldTree.props) else null
    )
}

private fun reconcileNewPlatformElement(root: Element, oldTree: InstantiatedElement?, args: ReconcileRunArguments): InstantiatedElement {
    return InstantiatedElement(
            type = root.type,
            props = root.props,
            children = root.children.map { reconcile(it, null, args) },
            instance = Later<Any>(),
            change = if (oldTree == null) Add() else Replace(oldTree.instance)
    )
}
