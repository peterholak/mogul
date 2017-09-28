@file:Suppress("unused")

package microdom

import drawing.Window

// Currently doesn't exist in the Kotlin Native stdlib
annotation class DslMarker

fun runKogulEngine(windowWidth: Int, windowHeight: Int) {
    val window = Window(windowWidth, windowHeight, 0xDDDDDD.color)
    val microdom = KogulEngine(window)
    window.runEfficientEventLoop()
    window.cleanup()
    println("Cleanup finished.")
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