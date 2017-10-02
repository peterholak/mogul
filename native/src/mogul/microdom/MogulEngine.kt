@file:Suppress("unused")

package mogul.microdom

import mogul.drawing.Event
import mogul.drawing.QuitEvent
import mogul.drawing.Window

//
//fun runMogulEngine(windowWidth: Int, windowHeight: Int, scene: Scene): Future<MogulEngine> {
//    val events = LinkedBlockingQueue<Event>()
//    val engine = CompletableFuture<MogulEngine>()
//    thread(name="MogulEngine") {
//        val window = Window(windowWidth, windowHeight, 0xDDDDDD.color, events)
//        engine.complete(MogulEngine(window, scene))
//        window.runEfficientEventLoop()
//        window.cleanup()
//        events.put(QuitEvent)
//    }
//    println("Cleanup finished.")
//    return engine
//}

// Uncomment for native:
// Currently doesn't exist in the Kotlin Native stdlib
annotation class DslMarker

fun runMogulEngine(windowWidth: Int, windowHeight: Int, scene: Scene, eventListener: (Event) -> Unit): MogulEngine {
    val window = Window(windowWidth, windowHeight, 0xDDDDDD.color, eventListener)
    val engine = MogulEngine(window, scene)
    return engine
}

class MogulEngine(val window: Window, var scene: Scene) {
    init {
        render()
    }

    fun render() {
        // TODO: maybe this should be async, just send an event to the Window to schedule a re-render
        window.draw { cairo ->
            scene.draw(cairo)
        }
    }

    fun runBlocking() {
        window.runEfficientEventLoop()
        window.cleanup()
        println("Cleanup finished.")
    }

    fun shutdown() {
        window.quit()
    }
}