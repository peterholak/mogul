package mogul.platform.jvm

import mogul.platform.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue

class QueueEventPubSub : EventPubSub {
    val subscribers = ConcurrentHashMap<EventType, ConcurrentLinkedQueue<EventHandler<*>>>()
    val everythingSubscribers = ConcurrentLinkedQueue<EventHandler<Event>>()

    override fun <T: Event> subscribe(type: EventType, handler: EventHandler<T>) {
        subscribers.getOrPut(type, { ConcurrentLinkedQueue() }).add(handler)
    }

    override fun <T: Event> unsubscribe(type: EventType, handler: EventHandler<T>) {
        subscribers[type]?.remove(handler)
    }

    override fun subscribeToEverything(handler: EventHandler<Event>) {
        everythingSubscribers.add(handler)
    }

    override fun unsubscribeFromEverything(handler: EventHandler<Event>) {
        everythingSubscribers.remove(handler)
    }

    val queue: BlockingQueue<Event> = LinkedBlockingQueue<Event>()

    override fun publish(event: Event) {
        queue.put(event)
    }

    fun waitForEvent() {
        val event = queue.take()
        // Notify subscribers on the same thread where events are normally processed.
        // This shit will be changed very soon anyway...
        subscribers[event.type]?.forEach {
            @Suppress("UNCHECKED_CAST")
            (it as EventHandler<Event>).invoke(event)
        }
        everythingSubscribers.forEach { it.invoke(event) }
    }

}
