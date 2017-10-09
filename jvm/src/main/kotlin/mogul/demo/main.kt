package mogul.demo

import mogul.microdom.MicroDom
import mogul.microdom.primitives.VerticalDirection
import mogul.platform.MouseEvent
import mogul.react.slow.*
import mogul.react.slow.dom.appKgx
import mogul.react.slow.dom.layoutBox
import mogul.react.slow.dom.runApp

data class MyAppState(
    val windowCount: Int,
    val globalCounter: Int
)
class MyApp : StatefulComponent<Nothing, MyAppState>() {

    override val initialState = MyAppState(windowCount = 1, globalCounter = 0)

    override fun render() = appKgx {

        (1..state.windowCount).map {
            window(title = "mogul-$platformName #$it", width = 800, height = 600, root = kgx {
                layoutBox(direction = VerticalDirection, spacing = 15) {
                    fourBoxes(
                            title = "Window #$it. Click on the small boxes.",
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
        // This is called separately to test the batching of updates.
        setState(state.copy(windowCount = state.windowCount + 1))
        setState(state.copy(globalCounter = state.globalCounter + 1))
    }

    fun onFewerWindows(event: MouseEvent) {
        setState(state.copy(windowCount = state.windowCount - 1))
    }

    fun incrementGlobalCounter(event: MouseEvent) {
        setState(state.copy(globalCounter = state.globalCounter + 1))
    }

}
val myAppType = ElementType("MyApp", { MyApp() })

fun main(args: Array<String>) {

    val (engine, events) = platformInit()
    val microDom = MicroDom(engine, events)
    runApp(microDom, myAppType)
//    runApp(microDom, liveReloadType)
}
