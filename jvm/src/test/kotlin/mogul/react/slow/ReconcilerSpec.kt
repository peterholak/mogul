package mogul.react.slow

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

// Basically the behavior should match the one described at https://reactjs.org/docs/reconciliation.html
@RunWith(JUnitPlatform::class)
object ReconcilerSpec : Spek({

    it("removes subtree when elements are of different type") {
        TODO()
    }

    it("preserves subtree when elements are of the same type") {
        TODO()
    }

})