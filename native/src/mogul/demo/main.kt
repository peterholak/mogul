package mogul.demo

import mogul.microdom.*
import mogul.microdom.primitives.*
import mogul.react.slow.*
import mogul.react.slow.dom.*

class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)
class TwoBoxesAndText : Component<TwoBoxesAndTextProps>() {

    override fun render() = kgx {
        layoutBox {
            box(style = s{ width = 50; height = 50; backgroundColor = props.firstColor })
            box(style = s{ width = 100; height = 100; backgroundColor = props.secondColor })
            -props.text
            -"!"
        }
    }
}
val TwoBoxesAndTextType = ElementType({ TwoBoxesAndText() })
fun KgxBuilder.twoBoxesAndText(firstColor: Color = Color.white, secondColor: Color = Color.black, text: String) {
    children.add(Element(TwoBoxesAndTextType, TwoBoxesAndTextProps(firstColor, secondColor, text)))
}

class FourBoxes : Component<Any>() {

    override fun render() = kgx {
        layoutBox(direction = VerticalDirection, spacing = 10) {
            twoBoxesAndText(text = "Hello", firstColor = 0xFF0000.color, secondColor = 0x0000FF.color)
            twoBoxesAndText(text = "World", firstColor = 0x0000FF.color, secondColor = 0xFF0000.color)
        }
    }

}
val FourBoxesType = ElementType({ FourBoxes() })
val KgxBuilder.fourBoxes; get() = children.add(Element(FourBoxesType, kotlin.Unit))

fun main(args: Array<String>) {
    val page = domRender(kgx {
        fourBoxes
    })

    runMogulEngine(800, 600, Scene(page))
}
