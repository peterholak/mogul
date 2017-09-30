@file:Suppress("unused")

package kogul.microdom

import kogul.drawing.Window
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlin.concurrent.thread

suspend fun runKogulEngine(windowWidth: Int, windowHeight: Int, scene: Scene): CompletableDeferred<KogulEngine> {
    val engine = CompletableDeferred<KogulEngine>()
    thread(name="KogulEngine") {
        val window = Window(windowWidth, windowHeight, 0xDDDDDD.color)
        engine.complete(KogulEngine(window, scene))
        window.runEfficientEventLoop()
        window.cleanup()
        println("Cleanup finished.")
    }
    return engine
}

// Uncomment for native:
//// Currently doesn't exist in the Kotlin Native stdlib
//annotation class DslMarker
//
//fun runKogulEngine(windowWidth: Int, windowHeight: Int, scene: Scene) {
//    val window = Window(windowWidth, windowHeight, 0xDDDDDD.color)
//    val microdom = KogulEngine(window, scene)
//    window.runEfficientEventLoop()
//    window.cleanup()
//    println("Cleanup finished.")
//}

class KogulEngine(val window: Window, var scene: Scene) {
    init {
        render()
    }

    fun render() {
        // TODO: maybe this should be async, just send an event to the Window to schedule a re-render
        window.draw { cairo ->
            scene.draw(cairo)
        }
    }

    fun shutdown() {
        window.quit()
    }
}