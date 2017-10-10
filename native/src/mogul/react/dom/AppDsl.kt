package mogul.react.dom

import mogul.microdom.Color
import mogul.microdom.color
import mogul.microdom.primitives.MicroDomMarker
import mogul.react.Element

@MicroDomMarker
class AppGuiBuilder {
    val children = mutableListOf<Element>()
    fun window(title: String, width: Int, height: Int, background: Color = 0xDDDDDD.color, root: Element) {
        children.add(Element(windowType, WindowProps(title, width, height, background), listOf(root)))
    }
}

fun appGui(appBuilder: AppGuiBuilder.() -> Unit): Element {
    val builder = AppGuiBuilder()
    appBuilder(builder)
    return Element(appType, Unit, builder.children)
}
