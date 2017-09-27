package engine

import drawing.Cairo

sealed class Node {
    abstract val style: Style
    abstract fun draw(cairo: Cairo)
    abstract fun layoutSize(cairo: Cairo): Size
}

abstract class Container : Node() {
    abstract val children: List<Node>
}

abstract class Leaf : Node()

class Scene(val root: Node) {
    fun draw(cairo: Cairo) {
        root.draw(cairo)
    }
}
