package mogul.react.slow.dom

import mogul.microdom.Color
import mogul.microdom.MicroDom
import mogul.microdom.Scene
import mogul.microdom.color
import mogul.platform.*
import mogul.react.slow.*

val appType = ElementType("app")
val windowType = ElementType("window")

data class WindowProps(val title: String, val width: Int, val height: Int, val background: Color, val autoClose: AutoClose = AutoClose.Close)

/**
 * Because windows can be closed with in a system way (if the autoClose prop permits it), it may sometimes
 * be necessary to re-create them. This class listens to close events of a window and re-creates it on update if necessary.
 */
class WindowLifecycle(var window: Window?, element: InstantiatedElement, val microDom: MicroDom) {

    val windowDestroyedHandler = { event: WindowDestroyedEvent ->
        if (event.window === window) {
            window = null
        }
    }

    init {
        microDom.events.subscribe(WindowDestroyed, windowDestroyedHandler)
        register(element)
    }

    private fun register(windowElement: InstantiatedElement) {
        microDom.registerWindow(window!!, Scene(constructDomNode(windowElement.children.single())))
    }

    fun update(windowElement: InstantiatedElement) {
        if (window == null) {
            val props = windowElement.props as WindowProps
            if (props.autoClose === AutoClose.CloseAndDoNotRecreate) {
                return
            }
            microDom.engine.runOnUiThreadAndWait {
                window = microDom.engine.createWindow(props.title, props.width, props.height, props.background)
                register(windowElement)
            }
        }else {
            microDom.sceneForWindow(window!!).replaceRoot(constructDomNode(windowElement.children.single()))
        }
    }

    fun destroy() {
        microDom.events.unsubscribe(WindowDestroyed, windowDestroyedHandler)
        val oldWindow = window ?: return
        window = null
        microDom.engine.destroyWindow(oldWindow)
    }
}

class Later<T>(var value: T? = null)
class AppUpdater(val root: Element, val microDom: MicroDom) : Updater {
    var oldTree: InstantiatedElement? = null

    override fun update() {
        val toRemove = mutableListOf<Remove>()
        val tree = ReactReconciler.reconcile(root, oldTree, ReconcileRunArguments(this, toRemove))
        oldTree = tree

        // The <app> can potentially be wrapped in a custom component...
        assert(tree.type === appType || tree.children.single().type === appType)
        val platformAppRoot = if (tree.type === appType) tree else tree.children.single()

        updateWindows(platformAppRoot.children, toRemove)
    }

    fun updateWindows(windows: List<InstantiatedElement>, toRemove: List<Remove>) {
        windows.forEach {
            // Later there can be more top-level components, such as notification icon controllers, etc.
            assert(it.type === windowType)
            when (it.change) {
                is Add -> createNewWindow(it)
                is Modify -> {
                    val lifecycle = (it.instance as Later<*>).value as WindowLifecycle
                    lifecycle.update(it)
                }
            }
        }

        toRemove.filter { it.element.type === windowType }.forEach {
            destroyWindow(it.element)
        }
    }

    fun createNewWindow(element: InstantiatedElement) {
        microDom.engine.runOnUiThreadAndWait {
            val props = element.props as WindowProps
            val window = microDom.engine.createWindow(props.title, props.width, props.height, props.background)
            @Suppress("UNCHECKED_CAST")
            (element.instance as Later<Any>).value = WindowLifecycle(window, element, microDom)
        }
    }

    fun destroyWindow(element: InstantiatedElement) {
        microDom.engine.runOnUiThreadAndWait {
            val windowLifecycle = (element.instance as Later<*>).value as WindowLifecycle
            windowLifecycle.destroy()
        }
    }
}

class AppKgxBuilder {
    val children = mutableListOf<Element>()
    fun window(title: String, width: Int, height: Int, background: Color = 0xDDDDDD.color, root: Element) {
        children.add(Element(windowType, WindowProps(title, width, height, background), listOf(root)))
    }
}

fun appKgx(appBuilder: AppKgxBuilder.() -> Unit): Element {
    val builder = AppKgxBuilder()
    appBuilder(builder)
    return Element(appType, Unit, builder.children)
}

fun runApp(microDom: MicroDom, root: Element) {
    microDom.engine.onEventLoopStarted.add {
        AppUpdater(root, microDom).update()
        if (microDom.engine.windows.isEmpty()) {
            // TODO: this should end asynchronously and without an error, just send a "reconcile done" event and
            // let the engine handle it
            microDom.engine.quit()
            error("Application has no windows.")
        }
    }
    microDom.runEventLoop()
}

fun runApp(microDom: MicroDom, rootType: ElementType) = runApp(microDom, Element(rootType, Unit))