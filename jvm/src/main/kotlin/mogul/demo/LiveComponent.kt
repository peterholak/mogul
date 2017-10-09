package mogul.demo

import mogul.react.slow.Component
import mogul.react.slow.Element
import mogul.react.slow.kgx

class LiveComponent : Component<Nothing>() {

    override fun render(): Element {
        return kgx {
            -"Hello world!"
        }
    }

}
