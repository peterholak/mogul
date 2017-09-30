package kogul.demo

import kogul.microdom.Scene
import kogul.microdom.runKogulEngine
import kogul.react.slow.dom.domRender
import kogul.react.slow.kgx

fun main(args: Array<String>) {

    val page = domRender(kgx { fourBoxes })

    val engine = runKogulEngine(800, 600, Scene(page)).get()
    while (!engine.window.shouldQuit) {
        val event = engine.window.eventListener.take()
        engine.scene.processEvent(event)
    }
}
