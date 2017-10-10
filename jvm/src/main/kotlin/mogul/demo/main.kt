package mogul.demo

import mogul.microdom.MicroDom
import mogul.microdom.primitives.VerticalDirection
import mogul.platform.MouseEvent
import mogul.react.ElementType
import mogul.react.StatefulComponent
import mogul.react.dom.appGui
import mogul.react.dom.layoutBox
import mogul.react.dom.runApp
import mogul.react.gui

data class MyAppState(
    val windowCount: Int,
    val globalCounter: Int
)

@Suppress("UNUSED_PARAMETER")
class MyApp : StatefulComponent<Nothing, MyAppState>() {

    override val initialState = MyAppState(windowCount = 1, globalCounter = 0)

    override fun render() = appGui {

        (1..state.windowCount).map {
            window(title = "mogul-$platformName #$it", width = 800, height = 600, root = gui {
                layoutBox(direction = VerticalDirection, spacing = 15) {
                    fourBoxes(
                            title = "Window #$it. Click on the small boxes.",
                            onMoreWindows = this@MyApp::onMoreWindows,
                            onFewerWindows = this@MyApp::onFewerWindows
                    )
                    -"Global counter: ${state.globalCounter}"
                    button(text = "Increment", onClick = this@MyApp::incrementGlobalCounter)
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
//    runApp(microDom, liveReloadType, LiveReloadProps(engine))
}
