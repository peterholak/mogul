package mogul.drawing

import mogul.microdom.Position

interface Event {
    val type: EventType
}
abstract class EventType

// This is done differently than ElementType(). Figure one which one is better. This seems heavier, because
// there will be extra classes defined...
object MouseDown : EventType()
object MouseUp : EventType()
object MouseMove : EventType()


object QuitEventType : EventType()
object QuitEvent : Event {
    override val type = QuitEventType
}

// TODO: what's with 0 1 when mouse leaves window
class MouseEvent(override val type: EventType, val position: Position) : Event
