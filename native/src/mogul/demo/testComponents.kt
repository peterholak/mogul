@file:Suppress("UNUSED_PARAMETER")

package mogul.demo

import mogul.generated.button
import mogul.generated.twoBoxesAndText
import mogul.microdom.*
import mogul.microdom.primitives.VerticalDirection
import mogul.platform.MouseEvent
import mogul.processor.GenerateBuilders
import mogul.react.*
import mogul.react.dom.box
import mogul.react.dom.layoutBox

data class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)
data class TwoBoxesState(val clickCount: Int)

@GenerateBuilders
class TwoBoxesAndText : StatefulComponent<TwoBoxesAndTextProps, TwoBoxesState>() {
    override val initialState = TwoBoxesState(clickCount = 1)

    override fun render() = gui {
        val boxStyle = style {
            border = Borders(
                    width = EdgeSizes(top = 2, right = 3, bottom = 3, left = 2),
                    color = EdgeColors(
                            top = 0xAAAAAA.color,
                            left = 0xAAAAAA.color,
                            bottom = 0x444444.color,
                            right = 0x444444.color
                    )
            )
        }

        layoutBox(spacing = 10, style = style { margin = 10.all }) {
            box(
                    style = style {
                        width = 50
                        height = 50
                        backgroundColor = props.firstColor
                    } + boxStyle,
                    events = events { click += this@TwoBoxesAndText::smallBoxClicked }
            )
            if (state.clickCount % 3 != 0) {
                box(style = style {
                    width = 100
                    height = 100
                    backgroundColor = props.secondColor
                } + boxStyle)
            } else {
                val smallBoxStyle = style { width = 50; height = 50; backgroundColor = props.secondColor } + boxStyle
                layoutBox(direction = VerticalDirection) {
                    box(style = smallBoxStyle)
                    box(style = smallBoxStyle)
                }
            }
            -props.text
            -state.clickCount.toString()
            -"!"
        }
    }

    fun smallBoxClicked(event: MouseEvent) {
        setState(state.copy(clickCount = state.clickCount + 1))
    }
}

data class FourBoxesProps(val title: String, val onMoreWindows: MouseEventHandler? = null, val onFewerWindows: MouseEventHandler? = null)
@GenerateBuilders
class FourBoxes : Component<FourBoxesProps>() {

    override fun render() = gui {
        layoutBox(direction = VerticalDirection, spacing = 10) {
            -props.title
            twoBoxesAndText(text = "Hello", firstColor = 0xFFEEEE.color, secondColor = 0xEEEEFF.color)
            twoBoxesAndText(text = "World", firstColor = 0xEEEEFF.color, secondColor = 0xFFEEEE.color)
            button(text = "More windows", onClick = props.onMoreWindows)
            button(text = "Fewer windows", onClick = props.onFewerWindows)
        }
    }

}

data class ButtonProps(val text: String, val style: Style = Style(), val onClick: MouseEventHandler? = null)

@GenerateBuilders
class Button : Component<ButtonProps>() {

    val buttonStyle = style {
        padding = 10.all
        margin = 20.left
        border = Borders(
                width = EdgeSizes(top = 1, right = 2, bottom = 2, left = 1),
                color = EdgeColors(
                        top = 0xAAAAAA.color,
                        left = 0xAAAAAA.color,
                        bottom = 0x444444.color,
                        right = 0x444444.color
                )
        )
    }

    override fun render(): Element {

        return gui {
            box(
                    style = buttonStyle + props.style,
                    hoverStyle = style { backgroundColor = Color.white },
                    mouseDownStyle = style { borders(1, Color.black) },
                    events = events { click += props.onClick }
            ) {
                -props.text
            }
        }
    }

}
