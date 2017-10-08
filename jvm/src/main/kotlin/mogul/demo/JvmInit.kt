package mogul.demo

import mogul.microdom.MicroDom
import mogul.platform.Engine
import mogul.platform.EventPubSub
import mogul.platform.SynchronousPubSub
import mogul.platform.jvm.QueueEventPubSub
import kotlin.concurrent.thread

enum class RunMode { SingleThreaded, Threaded }
val runMode = RunMode.Threaded
var platformRunEventLoop = ::platformRunEventLoopSingleThread

fun platformInit(): Pair<Engine, EventPubSub> {
    val events = if (runMode == RunMode.Threaded) QueueEventPubSub() else SynchronousPubSub()
    platformRunEventLoop = if (runMode == RunMode.Threaded)
        ::platformRunEventLoopThreaded
    else
        ::platformRunEventLoopSingleThread

    // Make sure we don't accidentally rely on implementation details - make the type just the cross-platform interface.
    val engine = mogul.platform.jvm.Engine(events)
    return Pair(engine, events)
}

fun platformRunEventLoopSingleThread(events: EventPubSub, microDom: MicroDom) {
    events.subscribeToEverything { event ->
        microDom.processEvent(event)
    }
    microDom.engine.runEfficientEventLoop()
    microDom.engine.cleanup()
}

fun platformRunEventLoopThreaded(events: EventPubSub, microDom: MicroDom) {

    microDom.engine.onEventLoopStarted.add {
        // Normally, these should run on the UI thread, but I'm experimenting with stuff...
        thread(name="UI Event Handlers") {
            val queueEvents = events as QueueEventPubSub

            queueEvents.subscribeToEverything { event ->
                microDom.processEvent(event)
            }

            while (!microDom.engine.quitting()) {
                queueEvents.waitForEvent()
            }
        }
        Unit
    }

    thread(name="Mogul UI") {
        microDom.engine.runEfficientEventLoop()
        microDom.engine.cleanup()
    }
}

val platformName = "jvm"
