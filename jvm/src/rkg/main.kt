package rkg

import engine.runKogulEngine
import kotlinx.coroutines.experimental.runBlocking
import react.ShallowRenderer
import react.WorldComponent
import react.createElement

fun main(args: Array<String>) {
    val renderer = ShallowRenderer()
    val element = createElement(WorldComponent::class)
    println(renderer.render(element))

    runBlocking {
        val engine = runKogulEngine(800, 600).await()
    }
}
