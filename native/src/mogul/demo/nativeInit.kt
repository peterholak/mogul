package mogul.demo

import mogul.platform.Engine
import mogul.platform.EventPubSub
import mogul.platform.SynchronousPubSub
import mogul.microdom.MicroDom

fun platformInit(): Pair<Engine, EventPubSub> {
    val events = SynchronousPubSub()
    // Make sure we don't accidentally rely on implementation details - make the type just the cross-platform interface.
    val engine = mogul.platform.native.Engine(events)

    return Pair(engine, events)
}

fun platformRunEventLoop(events: EventPubSub, microDom: MicroDom) {
    events.subscribeToEverything { event ->
        microDom.processEvent(event)
    }
    microDom.engine.runEfficientEventLoop()
    microDom.engine.cleanup()
}

val platformName = "native"
