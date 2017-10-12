package mogul.microdom

import mogul.platform.*


class Scene(root: Node) {

    var root: Node; private set
    // Obviously a better notification system will be used later (or a different mechanism altogether)
    internal var onInvalidated: (() -> Unit)? = null
    // Events that wait in queue until layout info becomes available (i.e. next draw)
    internal val noLayoutEventQueue = mutableListOf<Event>()

    init {
        this.root = root
    }

    val flatNodes: MutableList<Node> =
            if (root is Container)
                addToFlattenedList(mutableListOf(root), root)
            else
                mutableListOf(root)
    var hasLayoutInfo = false

    init {
        root.topLeft = Position(0, 0)
    }

    fun replaceRoot(newRoot: Node) {
        root = newRoot
        invalidate()
    }

    fun invalidate() {
        // TODO: this will really need thread safety later
        // It could also be optimized - often the layout does not need to be reset
        hasLayoutInfo = false
        flatNodes.clear()
        if (root is Container)
            addToFlattenedList(flatNodes, root as Container)
        else
            flatNodes.add(root)
        onInvalidated?.invoke()
    }

    private fun addToFlattenedList(list: MutableList<Node>, node: Container): MutableList<Node> {
        list.addAll(node.children)
        node.children.forEach {
            if (it is Container) {
                addToFlattenedList(list, it)
            }
        }
        return list
    }

    fun draw(cairo: Cairo) {
        if (!hasLayoutInfo) {
            root.populateLayoutSize(cairo)
        }
        root.draw(cairo)
        hasLayoutInfo = true
        processNoLayoutEventQueue()
    }

    private fun processNoLayoutEventQueue() {
        val shouldInvalidate = noLayoutEventQueue.any {
            processEvent(it, batched = true)
        }
        noLayoutEventQueue.clear()
        if (shouldInvalidate) {
            invalidate()
        }
    }

    fun processEvent(event: Event, batched: Boolean = false): Boolean {
        if (event !is MouseEvent) return false

        if (!hasLayoutInfo) {
            noLayoutEventQueue.add(event)
            return false
        }

        // TODO: a more efficient algorithm, maybe store lists of nodes by their state or something
        val atPosition = nodesAtPosition(event.position)

        // TODO: only invalidate if the style differs, and only the dirty rect, etc.
        var shouldInvalidate = false
        flatNodes.forEach {
            val hoverNow = atPosition.contains(it)
            when {
                it.state.hover && !hoverNow -> {
                    it.state = initialNodeState
                    it.fireEvent(MouseEvent(event.window, MouseOver, event.position))
                    shouldInvalidate = true
                }

                !it.state.hover && hoverNow -> {
                    it.state = NodeState(hover = true)
                    it.fireEvent(MouseEvent(event.window, MouseOut, event.position))
                    shouldInvalidate = true
                }

                hoverNow && event.type === MouseDown -> {
                    it.state = NodeState(hover = true, mouseDown = true)
                    shouldInvalidate = true
                }

                it.state.mouseDown && hoverNow && event.type === MouseUp -> {
                    it.state = NodeState(hover = true)
                    it.fireEvent(MouseEvent(event.window, Click, event.position))
                    shouldInvalidate = true
                }
            }
        }
        if (shouldInvalidate && !batched) {
            invalidate()
        }
        return shouldInvalidate
    }

    fun nodesAtPosition(position: Position): Set<Node> {
        if (!hasLayoutInfo)
            error("nodesAtPosition called while layout info is not available - events should be queued until then.")

        // TODO: obviously use some faster algorithm here later
        return flatNodes.filter { it.boundingRectangle()!!.contains(position) }.toSet()
    }
}
