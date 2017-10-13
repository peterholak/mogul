package mogul.kobx

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

class Observable<T>(private val kobx: KobX, private var currentValue: T) {

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (property is KMutableProperty<*>) {
            kobx.markRead(property)
        }
        return currentValue
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        currentValue = value
        kobx.runReactions(property)
    }
}
