@file:Suppress("unused")

package mogul.microdom

import mogul.demo.platformRunEventLoop
import mogul.microdom.primitives.Text
import mogul.platform.*

class WindowNotRegisteredException : Exception()
class MicroDom(val engine: Engine, val events: EventPubSub) {
    private val scenesByWindow = mutableMapOf<Window, Scene>().withDefault { Scene(Text("No Scene on this Window")) }

    fun sceneForWindow(window: Window) = scenesByWindow[window] ?: throw WindowNotRegisteredException()

    fun registerWindow(window: Window, scene: Scene) {
        scenesByWindow[window] = scene
        scene.onInvalidated = { engine.runOnUiThread {
            // Async operation, the window could have been destroyed in the meantime...
            if (scenesByWindow.contains(window)) {
                render(window)
            }
        }}
        render(window)
    }

    fun unregisterWindow(window: Window) {
        sceneForWindow(window).onInvalidated = null
        scenesByWindow.remove(window)
    }

    fun render(window: Window) {
        // TODO: maybe this should be async, just send an event to the Window to schedule a re-render
        window.draw { cairo ->
            scenesByWindow[window]?.draw(cairo)
                    ?: throw WindowNotRegisteredException()
        }
    }

    fun processEvent(event: Event) {
        if (event !is EventWithWindow) return

        if (event is WindowDestroyedEvent) {
            return unregisterWindow(event.window)
        }

        scenesByWindow[event.window]?.processEvent(event)
                ?: throw WindowNotRegisteredException()
    }

    fun shutdown() {
        engine.quit()
    }

    fun runEventLoop() {
        platformRunEventLoop(events, this)
    }
}