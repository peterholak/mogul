package react

import drawing.Cairo

class HelloComponent(props: Props, context: Context, updater: Updater) : Component(props, context, updater) {
    override fun render(): Element {
        return createElement(Div::class, DivProps(width = 100, height = 200), listOf(
                createElement(Div::class, DivProps(width = 50, height = 20))
        ))
    }

}

class WorldComponent(props: Props, context: Context, updater: Updater) : Component(props, context, updater) {
    override fun render(): Element {
        return createElement(HelloComponent::class)
    }

}

class Div(props: Props, context: Context, updater: Updater) : RawHtmlComponent(props, context, updater) {
    override fun draw(cairo: Cairo) {

    }

}
