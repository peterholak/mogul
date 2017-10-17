@file:Suppress("UNUSED_PARAMETER")

package mogul.kobx

import mogul.generated.button
import mogul.platform.MouseEvent
import mogul.processor.GenerateBuilders
import mogul.react.*
import mogul.react.dom.layoutBox

fun main(args: Array<String>) {
    val kobx = KobX()
    val demo = Demo(kobx)

    kobx.autorun {
        println("Reaction ${demo.inner.count}")
    }
    val stringReaction = kobx.autorun {
        println("StringReaction ${demo.inner.name}")
    }

    demo.inner.count = 5
    demo.inner.count++

    demo.inner.name = "What"

    kobx.dispose(stringReaction)

    demo.inner.name = "This"
}

class Inner(kobx: KobX) {
    var count by kobx.observable(0)
    var name by kobx.observable("Hello")
}

class Demo(kobx: KobX) {
    val inner = Inner(kobx)
}

@GenerateBuilders(observer = true)
class KobxCounter(kobx: KobX) : Component<Unit>() {

    var count by kobx.observable(0)

    override fun render(): Element {
        return gui {
            layoutBox {
                -"Local count using observables: $count"
                button(text="Increment", onClick = this@KobxCounter::incrementCounter)
            }
        }
    }

    fun incrementCounter(event: MouseEvent) {
        count++
    }
}

@GenerateBuilders(observer = true)
class GlobalKobxCounter(val store: DemoStore) : Component<Unit>() {

    override fun render(): Element {
        return gui {
            layoutBox {
                -"Global count using observables: ${store.count}"
                button(text="Increment", onClick = this@GlobalKobxCounter::incrementCounter)
            }
        }
    }

    fun incrementCounter(event: MouseEvent) {
        store.count++
    }
}
class DemoStore(kobx: KobX) {
    var count by kobx.observable(0)
}