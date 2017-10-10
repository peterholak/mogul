package mogul.demo.livereload

import mogul.react.Component
import mogul.react.Element
import mogul.react.gui

@Suppress("unused")
class LiveComponent : Component<Nothing>() {

    override fun render(): Element {
        return gui {
            -"Hello world!"
        }
    }

}
