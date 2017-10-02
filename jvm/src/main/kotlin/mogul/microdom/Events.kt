package mogul.microdom

import mogul.drawing.*
import mogul.microdom.primitives.MicroDomMarker

typealias Handler<T> = (T) -> Unit

// If it turns out there's a shit-ton of these, maybe make it backed by a map, just like Style
@MicroDomMarker
class Events {
    val map = mutableMapOf<EventType, MutableList<Handler<Event>>>()

    private fun <T: Event> addHandler(type: EventType, handler: Handler<T>) {
        @Suppress("UNCHECKED_CAST")
        map.getOrPut(type, { mutableListOf() }).add(handler as Handler<Event>)
    }
    fun mouseDown(handler: Handler<MouseEvent>) = addHandler(MouseDown, handler)
    fun mouseUp(handler: Handler<MouseEvent>) = addHandler(MouseUp, handler)
    fun mouseMove(handler: Handler<MouseEvent>) = addHandler(MouseMove, handler)
}

fun events(code: Events.() -> Unit): Events {
    val e = Events()
    code(e)
    return e
}
