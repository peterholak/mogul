package mogul.react.dom

import mogul.microdom.*
import mogul.microdom.primitives.*
import mogul.react.*

class InvalidElementType : Exception()

fun constructDomNode(e: InstantiatedElement): Node {
    if (e.type.isComponent()) {
        return constructDomNode(e.children.single())
    }

    val result: Node = when (e.type) {
        boxType -> {
            val props = e.props as BoxProps
            Box(props.style, props.hoverStyle, props.mouseDownStyle, props.events, e.children.map { constructDomNode(it) })
        }
        textType -> {
            val props = e.props as TextProps
            Text(props.text, props.style)
        }
//        stringType -> Text(e.props as String)
        layoutBoxType -> {
            val props = e.props as LayoutBoxProps
            LayoutBox(
                    props.direction,
                    props.spacing,
                    props.style,
                    props.events,
                    e.children.map { constructDomNode(it) }
            )
        }
        else -> throw InvalidElementType()
    }
    return e.populateLaterInstance(result)
}


fun updateDom(scene: Scene, root: InstantiatedElement, toRemove: List<InstantiatedElement>) {

    toRemove.forEach {
        if (it.type.isComponent()) {
            val node = it.children.single().castLaterInstance<Node>()
            node.parent?.children?.remove(node)
        }else {
            val node = it.castLaterInstance<Node>()
            node.parent?.children?.remove(node)
        }
    }

    when (root.change) {
        is Add ->
            error("Bad output from reconciler: cannot add new root.")

        is Replace ->
            scene.replaceRoot(constructDomNode(root))

        is Modify -> {
            val rootNode = root.castLaterInstance<Container>()
            updateNodeProps(rootNode, root.change.oldProps as NodeProps, root.props as NodeProps)
            root.children.forEach {
                updateDomElement(rootNode, it)
            }
            scene.invalidate()
        }
    }
}

/**
 * Updates the props and children of an existing DOM element, or creates a new DOM element if it didn't exist yet.
 */
private fun updateDomElement(parent: Container, e: InstantiatedElement) {
    // Skip over component elements
    if (e.type.isComponent()) {
        if (e.change is Replace) {
            parent.replaceChild(
                    e.change.oldComponent!!.children.single().castLaterInstance(),
                    constructDomNode(e.children.single())
            )
            return
        }else {
            return updateDomElement(parent, e.children.single())
        }
    }

    when (e.change) {
        is Add ->
            parent.children.add(constructDomNode(e))

        is Replace ->
            parent.replaceChild((e.change.oldInstance as Later<*>).value as Node, constructDomNode(e))

        is Modify -> {
            val instance = e.castLaterInstance<Node>()
            updateNodeProps(instance, e.change.oldProps as NodeProps, e.props as NodeProps)
            if (instance is Container) {
                e.children.forEach {
                    updateDomElement(instance, it)
                }
            }
        }
    }
}

@Suppress("UNUSED_PARAMETER")
private fun updateNodeProps(node: Node, oldProps: NodeProps, newProps: NodeProps) {
    node.style = newProps.style
    node.events = newProps.events

    // TODO: all the other props
    when(node) {
        is Text -> {
            val props = newProps as TextProps
            node.text = props.text
        }
    }
}
