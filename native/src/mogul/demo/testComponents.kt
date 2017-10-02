package mogul.demo

import mogul.microdom.*
import mogul.microdom.primitives.VerticalDirection
import mogul.react.slow.*
import mogul.react.slow.dom.box
import mogul.react.slow.dom.e
import mogul.react.slow.dom.layoutBox
import mogul.react.slow.dom.s

class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)
class TwoBoxesState : State({ TwoBoxesState() }) {
    var clickCount: Int by map
}
class TwoBoxesAndText : StatefulComponent<TwoBoxesAndTextProps, TwoBoxesState>() {
    // This is a pretty terrible way of initializing state, as it doesn't enforce required initial values...
    override var state = TwoBoxesState().apply {
        clickCount = 1
    }

    override fun render() = kgx {
        val boxStyle = style {
            border = Borders(
                    width = BoxSizes(top = 2, right = 3, bottom = 3, left = 2),
                    color = BoxColors(
                            top = 0xAAAAAA.color,
                            left = 0xAAAAAA.color,
                            bottom = 0x444444.color,
                            right = 0x444444.color
                    )
            )
        }

        layoutBox(spacing = 10, style = s{ margin = 10.all }) {
            box(
                    style = s{ width = 50; height = 50; backgroundColor = props.firstColor } + boxStyle,
                    events = e{ mouseDown { smallBoxClicked() } }
            )
            if (state.clickCount % 3 != 0) {
                box(style = s { width = 100; height = 100; backgroundColor = props.secondColor } + boxStyle)
            }else{
                layoutBox(direction = VerticalDirection) {
                    box(style = s { width = 50; height = 50; backgroundColor = props.secondColor } + boxStyle)
                    box(style = s { width = 50; height = 50; backgroundColor = props.secondColor } + boxStyle)
                }
            }
            -props.text
            -state.clickCount.toString()
            -"!"
        }
    }

    fun smallBoxClicked() {
        setState {
            clickCount++
        }
    }
}

// This ugly boilerplate would preferably be generated with a compiler plugin...
val twoBoxesAndTextType = ElementType({ TwoBoxesAndText() })
fun KgxBuilder.twoBoxesAndText(firstColor: Color = Color.white, secondColor: Color = Color.black, text: String) {
    children.add(Element(twoBoxesAndTextType, TwoBoxesAndTextProps(firstColor, secondColor, text)))
}

class FourBoxes : Component<Nothing>() {

    override fun render() = kgx {
        layoutBox(direction = VerticalDirection, spacing = 10) {
            twoBoxesAndText(text = "Hello", firstColor = 0xFFEEEE.color, secondColor = 0xEEEEFF.color)
            twoBoxesAndText(text = "World", firstColor = 0xEEEEFF.color, secondColor = 0xFFEEEE.color)
        }
    }

}
val fourBoxesType = ElementType({ FourBoxes() })
val KgxBuilder.fourBoxes; get() = children.add(Element(fourBoxesType, Unit))
