@file:Suppress("unused")

package mogul.microdom

import mogul.microdom.primitives.Text
import mogul.platform.Engine
import mogul.platform.Event
import mogul.platform.EventWithWindow
import mogul.platform.Window

class WindowNotRegisteredException : Exception()
class MicroDom(val engine: Engine) {
    private val sceneForWindow = mutableMapOf<Window, Scene>().withDefault { Scene(Text("No Scene on this Window")) }

    fun registerWindow(window: Window, scene: Scene) {
        sceneForWindow[window] = scene
        scene.onRootReplaced = { render(window) }
        render(window)
    }

    fun render(window: Window) {
        // TODO: maybe this should be async, just send an event to the Window to schedule a re-render
        window.draw { cairo ->
            sceneForWindow[window]?.draw(cairo)
                    ?: throw WindowNotRegisteredException()
        }
    }

    fun processEvent(event: Event) {
        if (event !is EventWithWindow) return

        sceneForWindow[event.window]?.processEvent(event)
                ?: throw WindowNotRegisteredException()
    }

    fun shutdown() {
        engine.quit()
    }
}