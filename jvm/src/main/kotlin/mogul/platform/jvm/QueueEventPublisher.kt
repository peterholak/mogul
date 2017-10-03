package mogul.platform.jvm

import mogul.platform.Event
import mogul.platform.EventPublisher
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class QueueEventPubSub : EventPublisher {
    val queue: BlockingQueue<Event> = LinkedBlockingQueue<Event>()

    override fun publish(event: Event) {
        queue.put(event)
    }

    fun waitForEvent() = queue.take()

}
