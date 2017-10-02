package mogul.microdom

import mogul.microdom.primitives.Box
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object NodeSpec : Spek({

    it("can be replaced by calling `replace`") {
        val child = Box(); val before = Box(); val after = Box()
        val parent = Box(children = listOf(before, child, after))
        val newChild = Box()

        child.replaceWith(newChild)
        assertEquals(listOf(before, newChild, after), parent.children)
    }

    it("correctly sets the parent on any node added to a container") {
        val childConstructor = Box()
        val childAddLater = Box()
        val parent = Box(children = listOf(childConstructor))
        parent.children.add(childAddLater)

        assertSame(parent, childConstructor.parent)
        assertSame(parent, childAddLater.parent)
    }

    // This behavior is kinda pointless for the purposes of this library
    it("sets the parent to null on a removed or replaced node") {
        val oldChild = Box(); val newChild = Box()
        val parent = Box(children = listOf(oldChild))

        parent.children.clear()
        assertNull(oldChild.parent)

        parent.children.add(oldChild)
        assertSame(parent, oldChild.parent)
        parent.children[0] = newChild
        assertNull(oldChild.parent)
        assertSame(parent, newChild.parent)
    }

    it("removes the child from the old parent when adding it to a new parent") {
        val child = Box()
        val oldParent = Box(children = listOf(child))
        val newParent = Box()

        newParent.children.add(child)
        assertSame(newParent, child.parent)
        assertTrue(oldParent.children.isEmpty())
    }

})