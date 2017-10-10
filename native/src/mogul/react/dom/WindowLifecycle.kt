package mogul.react.dom

import mogul.microdom.Color
import mogul.microdom.MicroDom
import mogul.microdom.Scene
import mogul.platform.AutoClose
import mogul.platform.Window
import mogul.platform.WindowDestroyed
import mogul.platform.WindowDestroyedEvent
import mogul.react.ElementType
import mogul.react.InstantiatedElement

val windowType = ElementType("window")

data class WindowProps(
    val title: String,
    val width: Int,
    val height: Int,
    val background: Color,
    val autoClose: AutoClose = AutoClose.Close
)

/**
 * Manages the lifecycle of a single window.
 *
 * Because windows can be closed with in a system way (if the autoClose prop permits it), it may sometimes
 * be necessary to re-create them. This class listens to close events of a window and re-creates it on update if necessary.
 */
class WindowLifecycle(var window: Window?, element: InstantiatedElement, val microDom: MicroDom) {

    val windowDestroyedHandler = { event: WindowDestroyedEvent ->
        if (event.window === window) {
            window = null
        }
    }

    init {
        microDom.events.subscribe(WindowDestroyed, windowDestroyedHandler)
        register(element)
    }

    private fun register(windowElement: InstantiatedElement) {
        microDom.registerWindow(
                window!!,
                Scene(constructDomNode(windowElement.children.single()))
        )
    }

    fun update(windowElement: InstantiatedElement, toRemove: List<InstantiatedElement>) {
        if (window == null) {
            val props = windowElement.props as WindowProps
            if (props.autoClose === AutoClose.CloseAndDoNotRecreate) {
                return
            }
            microDom.engine.runOnUiThreadAndWait {
                window = microDom.engine.createWindow(props.title, props.width, props.height, props.background)
                register(windowElement)
            }
        }else {
            updateDom(
                    microDom.sceneForWindow(window!!),
                    windowElement.children.single(), toRemove
            )
        }
    }

    fun destroy() {
        microDom.events.unsubscribe(WindowDestroyed, windowDestroyedHandler)
        val oldWindow = window ?: return
        window = null
        microDom.engine.destroyWindow(oldWindow)
    }
}
