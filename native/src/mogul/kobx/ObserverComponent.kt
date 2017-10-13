package mogul.kobx

import mogul.react.Component
import mogul.react.Element

abstract class ObserverComponent<out PropTypes>(val kobx: KobX) : Component<PropTypes>() {
    override final fun render(): Element {
        var result: Element? = null
        kobx.reaction({ result = renderObserved() }) {
            forceUpdate()
        }
        return result!!
    }

    // I could just let the users use the real render if I also made `render` called by a wrapper in `Component`
    // and then made the reconciler call the wrapper instead.
    abstract fun renderObserved(): Element
}
