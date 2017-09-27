@file:Suppress("unused")

package engine

import drawing.Window
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlin.concurrent.thread

suspend fun runKogulEngine(windowWidth: Int, windowHeight: Int): CompletableDeferred<KogulEngine> {
    val engine = CompletableDeferred<KogulEngine>()
    thread(name="KogulEngine") {
        val window = Window(windowWidth, windowHeight)
        engine.complete(KogulEngine(window))
        window.runEfficientEventLoop()
        window.cleanup()
        println("Cleanup finished.")
    }
    return engine
}

fun testScene(): Scene {
    return Scene(
            Row(children = listOf(
                Box(style { width = 100; height = 100}),
                Box(style { width = 100; height = 100 }, children = listOf(
                        Box(
                            style {
                                width = 50; height = 50
                                margin = BoxSizes(top = 10, left = 30)
                                backgroundColor = 0x00FF00.color
                                borderColor = Color.white
                            }
                        )
                )),
                Text("Hello world!", style { margin = BoxSizes(top = 5) })
            ), style = style { padding = BoxSizes(50); margin = BoxSizes(50) })
    )
}

class KogulEngine(val window: Window) {
    var scene = testScene()

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