package mogul.react.dom

import mogul.microdom.Events
import mogul.microdom.Style
import mogul.microdom.primitives.Direction
import mogul.microdom.primitives.HorizontalDirection
import mogul.react.ElementType

interface NodeProps {
    val style: Style
    val events: Events
}

data class BoxProps(
    override val style: Style = Style(),
    val hoverStyle: Style? = null,
    val mouseDownStyle: Style? = null,
    override val events: Events = Events()
) : NodeProps

data class TextProps(
    val text: String,
    override val style: Style = Style(),
    override val events: Events = Events()
) : NodeProps

data class LayoutBoxProps(
    val direction: Direction = HorizontalDirection,
    val spacing: Int = 0,
    override val style: Style = Style(),
    override val events: Events = Events()
) : NodeProps

val boxType = ElementType("dom.box")
val textType = ElementType("dom.text")
val layoutBoxType = ElementType("dom.layoutBox")
