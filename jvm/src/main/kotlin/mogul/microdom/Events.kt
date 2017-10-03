package mogul.microdom

import mogul.microdom.primitives.MicroDomMarker
import mogul.platform.*

typealias Handler<T> = (event: T) -> Unit
typealias MouseEventHandler = Handler<MouseEvent>

@MicroDomMarker
class Events {
    val map = mutableMapOf<EventType, MutableList<Handler<Event>>>()

    override fun equals(other: Any?) = other is Events && other.map == map
    override fun hashCode() = map.hashCode()

    private fun <T: Event> addHandler(type: EventType, handler: Handler<T>) {
        @Suppress("UNCHECKED_CAST")
        map.getOrPut(type, { mutableListOf() }).add(handler as Handler<Event>)
    }

    // In the React-like part, just one possible handler per event is enough, not sure about the general "microdom"
    // though.
    inner class EventDsl<T: Event>(val type: EventType) {
        operator fun plusAssign(handler: Handler<T>?) = handler?.let { addHandler(type, it) } ?: Unit
    }

    val mouseDown; get() = EventDsl<MouseEvent>(MouseDown)
    val mouseUp; get() = EventDsl<MouseEvent>(MouseUp)
    val mouseMove; get() = EventDsl<MouseEvent>(MouseMove)
}

fun events(code: Events.() -> Unit): Events {
    val e = Events()
    code(e)
    return e
}

/**
 * Shitty unfinished hack for the problem where each lambda gets re-created on every render(), and then it appears
 * as if props have changed (because the lambda instance is different) when in reality they haven't,
 * which can cause unnecessary re-renders.
 *
 * This class simply makes the caller assign a string tag (like a hashCode) and manually use a different one
 * whenever the code in the event handler changes. The idea is that it almost never changes, so 99% of the time,
 * it won't be necessary to change it and the default value (empty string) will be fine.
 *
 * Problem is that Kotlin interfaces don't support SAM conversions, so I cannot have something that can be
 * either a lambda or this object - unless I use [Any] and do a lot of ugly casts.
 *
 * The other alternative is to use bound method references instead of lambdas (or to be ok with those
 * extra re-renders, or to use something like `shouldComponentUpdate`).
 *
 * I'm currently experimenting with various approaches to things, so classes like this exist around the codebase.
 */
class UniqueLambdaWrapper<T>(val tag: String = "", val lambda: T) {
    override fun equals(other: Any?) = other is UniqueLambdaWrapper<*> && other.tag == tag
    override fun hashCode() = tag.hashCode()
}
/** See [UniqueLambdaWrapper] */
fun <T> u(tag: String = "", lambda: T) = UniqueLambdaWrapper(tag, lambda)