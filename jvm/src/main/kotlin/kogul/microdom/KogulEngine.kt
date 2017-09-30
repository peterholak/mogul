@file:Suppress("unused")

package kogul.microdom

import kogul.drawing.Event
import kogul.drawing.QuitEvent
import kogul.drawing.Window
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

fun runKogulEngine(windowWidth: Int, windowHeight: Int, scene: Scene): Future<KogulEngine> {
    val events = LinkedBlockingQueue<Event>()
    val engine = CompletableFuture<KogulEngine>()
    thread(name="KogulEngine") {
        val window = Window(windowWidth, windowHeight, 0xDDDDDD.color, events)
        engine.complete(KogulEngine(window, scene))
        window.runEfficientEventLoop()
        window.cleanup()
        events.put(QuitEvent)
    }
    println("Cleanup finished.")
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