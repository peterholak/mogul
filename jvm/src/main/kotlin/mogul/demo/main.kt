package mogul.demo

import mogul.microdom.MicroDom
import mogul.microdom.primitives.VerticalDirection
import mogul.platform.MouseEvent
import mogul.react.slow.*
import mogul.react.slow.dom.appKgx
import mogul.react.slow.dom.layoutBox
import mogul.react.slow.dom.runApp

class MyAppState : State({ MyAppState() }) {
    var windowCount: Int by map
    var globalCounter: Int by map
}
class MyApp : StatefulComponent<Nothing, MyAppState>() {
    override var state = MyAppState().apply { windowCount = 1; globalCounter = 0 }

    override fun render() = appKgx {

        (1..state.windowCount).map {
            window(title = "mogul-$platformName #$it", width = 800, height = 600, root = kgx {
                layoutBox(direction = VerticalDirection, spacing = 15) {
                    fourBoxes(
                            title = "Window #$it",
                            onMoreWindows = this@MyApp::onMoreWindows,
                            onFewerWindows = this@MyApp::onFewerWindows
                    )
                    -"Global counter: ${state.globalCounter}"
                    button(text="Increment", onClick = this@MyApp::incrementGlobalCounter)
                }
            })
        }

    }

    fun onMoreWindows(event: MouseEvent) {
        setState { windowCount++ }
    }

    fun onFewerWindows(event: MouseEvent) {
        setState { windowCount-- }
    }

    fun incrementGlobalCounter(event: MouseEvent) {
        setState { globalCounter++ }
    }

}
val myAppType = ElementType("MyApp", { MyApp() })

fun main(args: Array<String>) {

    val (engine, events) = platformInit()
    val microDom = MicroDom(engine, events)
    runApp(microDom, myAppType)

}
