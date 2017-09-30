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

fun KtxBuilder.twoBoxesAndText(
        firstColor: Color = Color.white,
        secondColor: Color = Color.black,
        text: String
) {
    children.add(Element(TwoBoxesAndText::class, TwoBoxesAndTextProps(firstColor, secondColor, text)))
}

class KtxBuilder {
    val children = mutableListOf<Element>()

    fun layoutBox(
            direction: Direction = HorizontalDirection,
            spacing: Int = 0,
            builder: KtxBuilder.() -> Unit
    ) {
        val nested = KtxBuilder()
        builder(nested)
        children.add(Element(LayoutBox::class, LayoutBoxProps(direction, spacing), nested.children))
    }

    fun box(style: Style = Style(), builder: (KtxBuilder.() -> Unit)? = null) {
        val nested = KtxBuilder()
        builder?.invoke(nested)
        children.add(Element(Box::class, BoxProps(style), nested.children))
    }

    fun text(text: String, style: Style = Style()) {
        children.add(Element(Text::class, TextProps(text, style)))
    }

    operator fun String.unaryMinus() = text(this)

    fun s(styleBuilder: Style.() -> Unit) = style(styleBuilder)
}

fun ktx(code: KtxBuilder.() -> Unit): Element {
    val builder = KtxBuilder()
    code(builder)
    return builder.children[0] // TODO: checks
}

class TwoBoxesAndTextProps(val firstColor: Color, val secondColor: Color, val text: String)
class TwoBoxesAndText : Component<TwoBoxesAndTextProps>() {

    override fun render() = ktx {
        layoutBox {
            box(style = s{ width = 50; height = 50; backgroundColor = props.firstColor })
            box(style = s{ width = 100; height = 100; backgroundColor = props.secondColor })
            -props.text
            -"!"
        }
    }
}

class FourBoxes : Component<Any>() {

    override fun render() = ktx{
        layoutBox(direction = VerticalDirection, spacing = 10) {
            twoBoxesAndText(text = "Hello", firstColor = 0xFF0000.color, secondColor = 0x0000FF.color)
            twoBoxesAndText(text = "World", firstColor = 0x0000FF.color, secondColor = 0xFF0000.color)
        }
    }

}
val KtxBuilder.fourBoxes; get() = children.add(Element(FourBoxes::class, Unit))

fun main(args: Array<String>) {

    val page = domRender(ktx { fourBoxes })

    runBlocking {
        runKogulEngine(800, 600, Scene(page))
    }
}
