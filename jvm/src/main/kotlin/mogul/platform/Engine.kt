package mogul.platform

import mogul.microdom.Color
import mogul.microdom.color

interface Engine {
    val windows: List<Window>
    fun createWindow(title: String, width: Int, height: Int, background: Color = 0xDDDDDD.color): Window
    fun runEfficientEventLoop()
    fun runGameEventLoop()
    fun quit()
    fun cleanup()
    fun quitting(): Boolean
}

interface EventPublisher {
    fun publish(event: Event)
}
