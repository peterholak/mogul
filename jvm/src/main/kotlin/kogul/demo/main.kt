package kogul.demo

import kogul.microdom.KogulEngine
import kogul.microdom.runKogulEngine
import kogul.react.slow.dom.domRender
import kogul.react.slow.kgx

fun main(args: Array<String>) {

    var engine: KogulEngine? = null
    val scene = domRender(kgx { fourBoxes }, { engine?.render() })

    engine = runKogulEngine(800, 600, scene).get()
    while (!engine.window.shouldQuit) {
        val event = engine.window.eventListener.take()
        engine.scene.processEvent(event)
    }
}
