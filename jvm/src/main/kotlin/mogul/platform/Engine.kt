package mogul.platform

import mogul.microdom.Color
import mogul.microdom.Position
import mogul.microdom.color

interface Engine {
    val windows: List<Window>

    // This is handled separately from other events to make it work with QueueEventPublisher. Cleanup of this
    // shit definitely needed.
    var onEventLoopStarted: MutableList<() -> Unit>

    fun createWindow(title: String, width: Int, height: Int, background: Color = 0xDDDDDD.color, autoClose: AutoClose = AutoClose.Close): Window
    fun destroyWindow(window: Window)
    fun runOnUiThread(code: () -> Unit)
    fun runOnUiThreadAndWait(code: () -> Unit)
    fun runEfficientEventLoop()
    fun runGameEventLoop()
    fun quit()
    fun cleanup()
    fun quitting(): Boolean
    fun mouseState(): MouseState
}

/** TODO: support for more buttons, more pointers in fact, but for now, this will suffice */
enum class MouseButton { Left, Middle, Right }
class MouseState(val position: Position, val buttons: Set<MouseButton>)

interface EventPublisher {
    fun publish(event: Event)
}

typealias EventHandler<T> = (T) -> Unit
interface EventSubscriber {
    fun <T: Event> subscribe(type: EventType, handler: EventHandler<T>)
    fun <T: Event> unsubscribe(type: EventType, handler: EventHandler<T>)
    fun subscribeToEverything(handler: EventHandler<Event>)
    fun unsubscribeFromEverything(handler: EventHandler<Event>)
}

interface EventPubSub : EventPublisher, EventSubscriber