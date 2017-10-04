package mogul.platform

class SynchronousPubSub : EventPubSub {
    val subscribers = mutableMapOf<EventType, MutableList<EventHandler<*>>>()
    val everythingSubscribers = mutableListOf<EventHandler<Event>>()

    override fun <T: Event> subscribe(type: EventType, handler: EventHandler<T>) {
        subscribers.getOrPut(type, { mutableListOf() }).add(handler)
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

    override fun publish(event: Event) {
        subscribers[event.type]?.forEach {
            @Suppress("UNCHECKED_CAST")
            (it as EventHandler<Event>).invoke(event)
        }
        everythingSubscribers.forEach { it.invoke(event) }
    }

}
