@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package mogul.generated

import mogul.react.Element
import mogul.react.ElementType
import mogul.react.GuiBuilder

val buttonType = ElementType("Button", { container -> mogul.demo.Button() })
fun GuiBuilder.button(text: String, style: mogul.microdom.Style = mogul.microdom.Style(), onClick: Function1<mogul.platform.MouseEvent,kotlin.Unit>? = null) {
    children.add(Element(buttonType, mogul.demo.ButtonProps(text, style, onClick)))
}

val fourBoxesType = ElementType("FourBoxes", { container -> mogul.demo.FourBoxes() })
fun GuiBuilder.fourBoxes(title: String, onMoreWindows: Function1<mogul.platform.MouseEvent,kotlin.Unit>? = null, onFewerWindows: Function1<mogul.platform.MouseEvent,kotlin.Unit>? = null) {
    children.add(Element(fourBoxesType, mogul.demo.FourBoxesProps(title, onMoreWindows, onFewerWindows)))
}

val twoBoxesAndTextType = ElementType("TwoBoxesAndText", { container -> mogul.demo.TwoBoxesAndText() })
fun GuiBuilder.twoBoxesAndText(firstColor: mogul.microdom.Color, secondColor: mogul.microdom.Color, text: String) {
    children.add(Element(twoBoxesAndTextType, mogul.demo.TwoBoxesAndTextProps(firstColor, secondColor, text)))
}

val globalKobxCounterType = ElementType("GlobalKobxCounter", { container -> mogul.kobx.ObserverComponent(container.get("KobX"), mogul.kobx.GlobalKobxCounter(container.get("DemoStore"))) })
fun GuiBuilder.globalKobxCounter() {
    children.add(Element(globalKobxCounterType, Unit))
}

val kobxCounterType = ElementType("KobxCounter", { container -> mogul.kobx.ObserverComponent(container.get("KobX"), mogul.kobx.KobxCounter(container.get("KobX"))) })
fun GuiBuilder.kobxCounter() {
    children.add(Element(kobxCounterType, Unit))
}