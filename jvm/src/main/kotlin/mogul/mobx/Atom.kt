package mogul.mobx

class Atom(val name: String) {

    fun onBecomeUnobserved() {

    }

    fun reportChanged() {

    }

    fun reportObserved() {

    }

    override fun toString() = name
}