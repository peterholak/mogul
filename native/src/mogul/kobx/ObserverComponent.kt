package mogul.kobx

import mogul.react.Component
import mogul.react.ComponentDecorator
import mogul.react.Element

class ObserverComponent<out PropTypes>(val kobx: KobX, inner: Component<PropTypes>) :
        ComponentDecorator<PropTypes>(inner)
{
    var previousReaction: Reaction? = null
    override fun render(): Element {
        var result: Element? = null
        previousReaction?.let { kobx.dispose(it) }
        previousReaction = kobx.reaction(
                trackCode = { result = inner.render() },
                reactionCode = { forceUpdate() }
        )
        return result!!
    }
}
