package mogul.demo

import mogul.microdom.*
import mogul.microdom.primitives.VerticalDirection
import mogul.platform.MouseEvent
import mogul.react.slow.*
import mogul.react.slow.dom.box
import mogul.react.slow.dom.e
import mogul.react.slow.dom.layoutBox
import mogul.react.slow.dom.s

data class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)
data class TwoBoxesState(val clickCount: Int)

class TwoBoxesAndText : StatefulComponent<TwoBoxesAndTextProps, TwoBoxesState>() {
    override val initialState = TwoBoxesState(clickCount = 1)

    override fun render() = kgx {
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

        layoutBox(spacing = 10, style = s{ margin = 10.all }) {
            box(
                    style = s{
                        width = 50
                        height = 50
                        backgroundColor = props.firstColor
                    } + boxStyle,
                    events = e{ click += this@TwoBoxesAndText::smallBoxClicked }
            )
            if (state.clickCount % 3 != 0) {
                box(style = s {
                    width = 100;
                    height = 100;
                    backgroundColor = props.secondColor
                } + boxStyle)
            }else{
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

// This ugly boilerplate would preferably be generated with a compiler plugin...
val twoBoxesAndTextType = ElementType("TwoBoxesAndText", { TwoBoxesAndText() })
fun KgxBuilder.twoBoxesAndText(firstColor: Color = Color.white, secondColor: Color = Color.black, text: String) {
    children.add(Element(twoBoxesAndTextType, TwoBoxesAndTextProps(firstColor, secondColor, text)))
}

data class FourBoxesProps(val title: String, val onMoreWindows: MouseEventHandler? = null, val onLessWindows: MouseEventHandler? = null)
class FourBoxes : Component<FourBoxesProps>() {

    override fun render() = kgx {
        layoutBox(direction = VerticalDirection, spacing = 10) {
            -props.title
            twoBoxesAndText(text = "Hello", firstColor = 0xFFEEEE.color, secondColor = 0xEEEEFF.color)
            twoBoxesAndText(text = "World", firstColor = 0xEEEEFF.color, secondColor = 0xFFEEEE.color)
            button(text = "More windows", onClick = props.onMoreWindows)
            button(text = "Fewer windows", onClick = props.onLessWindows)
        }
    }

}
val fourBoxesType = ElementType("FourBoxes", { FourBoxes() })
fun KgxBuilder.fourBoxes(title: String, onMoreWindows: MouseEventHandler? = null, onFewerWindows: MouseEventHandler? = null) =
    children.add(Element(fourBoxesType, FourBoxesProps(title, onMoreWindows, onFewerWindows)))

data class ButtonProps(val text: String, val style: Style = Style(), val onClick: MouseEventHandler? = null)
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

        return kgx {
            box(
                    style = buttonStyle + props.style,
                    hoverStyle = style{ backgroundColor = Color.white },
                    mouseDownStyle = style{ borders(1, Color.black) },
                    events = e{ click += props.onClick }
            ) {
                -props.text
            }
        }
    }

}
val buttonType = ElementType("Button", { Button() })
fun KgxBuilder.button(text: String, style: Style = Style(), onClick: MouseEventHandler? = null) =
    children.add(Element(buttonType, ButtonProps(text, style, onClick)))
