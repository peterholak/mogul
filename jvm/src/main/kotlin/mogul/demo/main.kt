package mogul.demo

import mogul.microdom.Container
import mogul.microdom.MogulEngine
import mogul.microdom.runMogulEngine
import mogul.microdom.testScene
import mogul.react.slow.dom.domRender
import mogul.react.slow.kgx

fun main(args: Array<String>) {

    var engine: MogulEngine? = null
    val scene = domRender(kgx { fourBoxes }, { engine?.render() })

//    val scene = testScene()

    engine = runMogulEngine(800, 600, scene).get()
    val greenBox = ((((scene.root as Container).children[1]) as Container).children[0])
//    println(greenBox.parent)
    while (!engine.window.shouldQuit) {
        val event = engine.window.eventListener.take()
        engine.scene.processEvent(event)
    }
}
