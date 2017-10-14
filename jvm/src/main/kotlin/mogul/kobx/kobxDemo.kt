@file:Suppress("UNUSED_PARAMETER")

package mogul.kobx

import mogul.demo.button
import mogul.platform.MouseEvent
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
/**
 * For now just using this, later I'll figure out the best way to inject dependencies into components
 * and how to make it as seamless as possible for the user, while still being fully configurable.
 */
val globalKobx = KobX()
val kobxCounterType = ElementType("KobxCounter", { ObserverComponent(globalKobx, KobxCounter(globalKobx)) })
fun GuiBuilder.kobxCounter() {
    children.add(Element(kobxCounterType, Unit))
}

class GlobalKobxCounter(kobx: KobX) : Component<GlobalKobxCounter.Props>() {
    /**
     * The props must have the same kobx instance as the component, but I can't think of any
     * way to get it there automatically right now. See also comment for [globalKobx].
     */
    class Props(val store: DemoStore)

    override fun render(): Element {
        return gui {
            layoutBox {
                -"Global count using observables: ${props.store.count}"
                button(text="Increment", onClick = this@GlobalKobxCounter::incrementCounter)
            }
        }
    }

    fun incrementCounter(event: MouseEvent) {
        props.store.count++
    }
}
class DemoStore(kobx: KobX) {
    var count by kobx.observable(0)
}
val demoStore = DemoStore(globalKobx)
val globalKobxCounterType = ElementType("GlobalKobxCounter", { ObserverComponent(globalKobx, GlobalKobxCounter(globalKobx)) })
fun GuiBuilder.globalKobxCounter() {
    children.add(Element(globalKobxCounterType, GlobalKobxCounter.Props(demoStore)))
}