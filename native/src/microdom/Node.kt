package microdom

import drawing.Cairo

sealed class Node {
    abstract val style: Style
    abstract fun draw(cairo: Cairo)

    /** Returns what the size of the content would be, if not affected by width/height style attributes. */
    abstract fun defaultInnerSize(cairo: Cairo): Size

    /** Returns the actual size of content, after width/height style attributes, padding, and borders are taken into account. */
    fun innerSize(cairo: Cairo): Size {
        val default by lazy { defaultInnerSize(cairo) }
        val shrunk by lazy { layoutSize(cairo) - style.padding - style.margin - style.border?.width }
        return Size(
                if (style.width == null) default.width else shrunk.width,
                if (style.height == null) default.height else shrunk.height
        )
    }

    /** Returns the full size of the element, including the padding, border and margins. */
    fun layoutSize(cairo: Cairo): Size {
        val defaultSize by lazy { defaultInnerSize(cairo) + style.padding + style.border?.width }
        return Size(
                style.width ?: defaultSize.width,
                style.height ?: defaultSize.height
        ) + style.margin
    }
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
