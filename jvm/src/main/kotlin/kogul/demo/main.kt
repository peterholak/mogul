package kogul.demo

import kogul.microdom.*
import kogul.microdom.primitives.*
import kotlinx.coroutines.experimental.runBlocking
import kogul.react.slow.Component
import kogul.react.slow.Element
import kogul.react.slow.dom.BoxProps
import kogul.react.slow.dom.LayoutBoxProps
import kogul.react.slow.dom.TextProps
import kogul.react.slow.dom.domRender

class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)

class TwoBoxesAndText : Component<TwoBoxesAndTextProps>() {

    override fun render() =
        Element(LayoutBox::class, props = LayoutBoxProps(), children = listOf(
                Element(Box::class, props = BoxProps(style {
                    width = 50; height = 50; backgroundColor = props?.firstColor
                })),
                Element(Box::class, props = BoxProps(style {
                    width = 100; height = 100; backgroundColor = props?.secondColor
                })),
                Element(Text::class, props = TextProps(props?.text ?: "NO TEXT"))
        ))
}

fun twoBoxesAndText(code: TwoBoxesAndText.() -> Unit) {
    val element = Element(TwoBoxesAndText::class)
}

class FourBoxes : Component<Any>() {

    override fun render() =
        Element(LayoutBox::class, props = LayoutBoxProps(VerticalDirection), children = listOf(
                Element(TwoBoxesAndText::class, props = TwoBoxesAndTextProps(0xFF0000.color, 0x0000FF.color, "Hello")),
                Element(TwoBoxesAndText::class, props = TwoBoxesAndTextProps(0x0000FF.color, 0xFF0000.color, "World"))
        ))

}

fun main(args: Array<String>) {

    val page = domRender(Element(FourBoxes::class))

    runBlocking {
        runKogulEngine(800, 600, Scene(page))
    }
}
