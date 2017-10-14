package mogul.kobx

import mogul.react.Component
import mogul.react.ComponentDecorator
import mogul.react.Element

class ObserverComponent<out PropTypes>(val kobx: KobX, inner: Component<PropTypes>) :
        ComponentDecorator<PropTypes>(inner)
{
    override fun render(): Element {
        var result: Element? = null
        kobx.reaction({ result = inner.render() }) {
            forceUpdate()
        }
        return result!!
    }
}
