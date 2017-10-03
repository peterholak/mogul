package mogul.demo

import mogul.microdom.*
import mogul.platform.QuitEvent
import mogul.platform.Window
import mogul.platform.Engine as EngineInterface
import mogul.platform.jvm.Engine
import mogul.platform.jvm.QueueEventPubSub
import mogul.react.slow.dom.domRender
import mogul.react.slow.kgx
import java.util.concurrent.CompletableFuture
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val events = QueueEventPubSub()
    // Make sure we don't accidentally rely on implementation details - make the type just the cross-platform interface.
    val engine: EngineInterface = Engine(events)

    val windowFuture = CompletableFuture<Window>()
    thread(name="MogulEngine") {
        // Window must be created in the same thread where the event loop is.
        windowFuture.complete(engine.createWindow("mogul-jvm", 800, 600))
        engine.runEfficientEventLoop()
        engine.cleanup()
        events.publish(QuitEvent)
    }
    val window = windowFuture.get()

    val microDom = MicroDom(engine)
    microDom.registerWindow(window, domRender(kgx { fourBoxes }))

    while (!engine.quitting()) {
        val event = events.waitForEvent()
        microDom.processEvent(event)
    }
}
