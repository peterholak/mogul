package mogul.demo

import mogul.platform.Engine
import mogul.platform.EventPubSub
import mogul.platform.QuitEvent
import mogul.platform.jvm.QueueEventPubSub
import kotlin.concurrent.thread

fun platformInit(): Pair<Engine, EventPubSub> {
    val events = QueueEventPubSub()
    // Make sure we don't accidentally rely on implementation details - make the type just the cross-platform interface.
    val engine = mogul.platform.jvm.Engine(events)

    thread(name="MogulEngine") {
        engine.runEfficientEventLoop()
        engine.cleanup()
        events.publish(QuitEvent)
    }

    return Pair(engine, events)
}

val platformName = "jvm"
