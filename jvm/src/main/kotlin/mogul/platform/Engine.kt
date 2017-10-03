package mogul.platform

import mogul.microdom.Color
import mogul.microdom.color

interface Engine {
    val windows: List<Window>
    fun createWindow(title: String, width: Int, height: Int, background: Color = 0xDDDDDD.color, autoClose: AutoClose = AutoClose.Close): Window
    fun destroyWindow(window: Window)
    fun runOnUiThread(code: () -> Unit)
    fun runOnUiThreadAndWait(code: () -> Unit)
    fun runEfficientEventLoop()
    fun runGameEventLoop()
    fun quit()
    fun cleanup()
    fun quitting(): Boolean
}

interface EventPublisher {
    fun publish(event: Event)
}

typealias EventHandler<T> = (T) -> Unit
interface EventSubscriber {
    fun <T: Event> subscribe(type: EventType, handler: EventHandler<T>)
    fun <T: Event> unsubscribe(type: EventType, handler: EventHandler<T>)
    fun waitForEvent(): Event
}

interface EventPubSub : EventPublisher, EventSubscriber