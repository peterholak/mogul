package mogul.demo

import mogul.microdom.primitives.VerticalDirection
import mogul.react.slow.Component
import mogul.react.slow.Element
import mogul.react.slow.dom.layoutBox
import mogul.react.slow.kgx

class LiveComponent : Component<Nothing>() {

    override fun render(): Element {
        return kgx {
            layoutBox(spacing = 50, direction = VerticalDirection) {
                (1..10).forEach {
                    button("Button $it", onClick = { println("Clicked $it")})
                }
            }
        }
    }

}
