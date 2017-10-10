package mogul.react.dom

import mogul.microdom.Scene
import mogul.microdom.primitives.Box
import mogul.microdom.primitives.LayoutBox
import mogul.microdom.primitives.Text
import mogul.microdom.style
import mogul.react.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DomUpdateSpec : Spek({

    fun Box.unchanged() =
        InstantiatedElement(boxType, BoxProps(), emptyList(), Later(this), null)

    fun Box.modified(children: List<InstantiatedElement> = emptyList(), props: BoxProps = BoxProps(), oldProps: BoxProps = BoxProps()) =
        InstantiatedElement(boxType, props, children, Later(this), Modify(oldProps))

    fun newBox(props: BoxProps = BoxProps(), children: List<InstantiatedElement> = emptyList()) =
        InstantiatedElement(boxType, props, children, Later<Box>(), Add())

    fun replaceLayoutBox(oldInstance: Any) =
        InstantiatedElement(layoutBoxType, LayoutBoxProps(), emptyList(), Later<LayoutBox>(), Replace(oldInstance))

    fun Text.modified(props: TextProps, oldProps: TextProps = TextProps(this.text)) =
        InstantiatedElement(textType, props, emptyList(), Later(this), Modify(oldProps))

    it("removes all the elements in `toRemove`") {
        val removedBox = Box()
        val oldRoot = Box(
                children = listOf(removedBox)
        )
        val scene = Scene(oldRoot)

        val toRemove = listOf(removedBox.unchanged())
        val rootElement = oldRoot.modified()

        updateDom(scene, rootElement, toRemove)

        assertSame(oldRoot, scene.root)
        assertTrue(oldRoot.children.isEmpty())
    }

    it("replaces scene root if it has been removed") {
        val oldRoot = Box()
        val scene = Scene(oldRoot)

        val vdomRoot = replaceLayoutBox(oldRoot)

        val toRemove = listOf(oldRoot.unchanged())
        updateDom(scene, vdomRoot, toRemove)

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
        val root = Box(style { width = 50 }, children = listOf(child))
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

    // TODO: test that replaced elements are added to the correct position

})
