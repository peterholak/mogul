package mogul.react.slow.dom

import mogul.microdom.Scene
import mogul.microdom.primitives.Box
import mogul.microdom.primitives.LayoutBox
import mogul.microdom.primitives.Text
import mogul.microdom.style
import mogul.react.slow.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DomUpdateSpec : Spek({

    fun Box.unchanged() =
        InstantiatedElement(boxType, BoxProps(), emptyList(), this, null)

    fun Box.modified(children: List<InstantiatedElement> = emptyList(), props: BoxProps = BoxProps()) =
        InstantiatedElement(boxType, BoxProps(), children, this, Modify(props))

    fun newBox(props: BoxProps = BoxProps(), children: List<InstantiatedElement> = emptyList()) =
        InstantiatedElement(boxType, props, children, Later<Box>(), Add())

    fun newLayoutBox() =
        InstantiatedElement(layoutBoxType, LayoutBoxProps(), emptyList(), Later<LayoutBox>(), Add())

    fun Text.modified(props: TextProps) =
        InstantiatedElement(textType, props, emptyList(), this, Modify(props))

    it("removes all the elements in `toRemove`") {
        val removedBox = Box()
        val oldRoot = Box(
                children = listOf(removedBox)
        )
        val scene = Scene(oldRoot)

        val toRemove = listOf(Remove(removedBox.unchanged()))
        val rootElement = oldRoot.modified()

        updateDom(scene, rootElement, toRemove)

        assertSame(oldRoot, scene.root)
        assertTrue(oldRoot.children.isEmpty())
    }

    it("replaces scene root if it has been removed") {
        val oldRoot = Box()
        val scene = Scene(oldRoot)

        val vdomRoot = newLayoutBox()

        updateDom(scene, vdomRoot, listOf(Remove(oldRoot.unchanged())))

        assertTrue(scene.root is LayoutBox)
    }

    it("adds new nodes to the appropriate parent") {
        val oldLevel3 = Box()
        val level2 = Box(children = listOf(oldLevel3))
        val root = Box(children = listOf(level2))
        val scene = Scene(root)

        val vdomRoot = root.modified(listOf(
                level2.modified(listOf(
                        oldLevel3.unchanged(),
                        newBox(BoxProps(style { width = 10 }))
                ))
        ))

        updateDom(scene, vdomRoot, emptyList())

        assertSame(root, scene.root)
        assertEquals(listOf(level2), root.children)
        val newLevel3 = level2.children.last()
        assertEquals(listOf(oldLevel3, newLevel3), level2.children)
        assertEquals(style { width = 10 }, newLevel3.style)
    }

    it("changes the props on existing modified nodes") {
        val child = Text("Hello")
        val root = Box(style { width = 50 })
        val scene = Scene(root)

        val vdomRoot = root.modified(
                children = listOf(
                        child.modified(TextProps("World"))
                ),
                props = BoxProps(style { height = 50 })
        )

        updateDom(scene, vdomRoot, emptyList())

        assertEquals(style { height = 50 }, root.style)
        assertEquals("World", (root.children.single() as Text).text)
    }

})
