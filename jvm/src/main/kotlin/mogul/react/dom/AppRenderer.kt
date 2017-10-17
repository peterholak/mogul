package mogul.react.dom

import mogul.microdom.MicroDom
import mogul.react.*
import mogul.react.injection.ServiceContainer

val appType = ElementType("app")

class AppUpdater(val root: Element, val microDom: MicroDom, val container: ServiceContainer) : Updater {
    var oldTree: InstantiatedElement? = null
    var updateQueued = false

    override fun queueUpdate() {
        if (updateQueued) return

        updateQueued = true
        microDom.engine.runOnUiThread {
            doUpdate()
            updateQueued = false
        }
    }

    fun doUpdate() {
        val toRemove = mutableListOf<InstantiatedElement>()
        val tree = reconcile(root, oldTree, ReconcileRunArguments(this, container, toRemove))
        oldTree = tree

        // The <app> can potentially be wrapped in a custom component...
        assert(tree.type === appType || tree.children.single().type === appType)
        val platformAppRoot = if (tree.type === appType) tree else tree.children.single()

        updateWindows(platformAppRoot.children, toRemove)
    }

    fun updateWindows(windows: List<InstantiatedElement>, toRemove: List<InstantiatedElement>) {
        windows.forEach {
            // Later there can be more top-level components, such as notification icon controllers, etc.
            assert(it.type === windowType)
            when (it.change) {
                is Add -> createNewWindow(it)

                is Replace -> {
                    destroyWindow(it.change.oldInstance as WindowLifecycle)
                    createNewWindow(it)
                }

                is Modify -> {
                    it.castLaterInstance<WindowLifecycle>().update(it, toRemove)
                }
            }
        }

        toRemove.filter { it.type === windowType }.forEach {
            destroyWindow(it.castLaterInstance())
        }
    }

    fun createNewWindow(element: InstantiatedElement) {
        microDom.engine.runOnUiThreadAndWait {
            val props = element.props as WindowProps
            val window = microDom.engine.createWindow(props.title, props.width, props.height, props.background)
            element.populateLaterInstance(WindowLifecycle(window, element, microDom))
        }
    }

    fun destroyWindow(lifecycle: WindowLifecycle) {
        microDom.engine.runOnUiThreadAndWait {
            lifecycle.destroy()
        }
    }
}

fun runApp(microDom: MicroDom, root: Element, container: ServiceContainer = ServiceContainer()) {
    microDom.engine.onEventLoopStarted.add {
        AppUpdater(root, microDom, container).doUpdate()
        if (microDom.engine.windows.isEmpty()) {
            // TODO: this should end asynchronously and without an error, just send a "reconcile done" event and
            // let the engine handle it
            microDom.engine.quit()
            error("Application has no windows.")
        }
    }
    microDom.runEventLoop()
}

fun runApp(microDom: MicroDom, rootType: ElementType, rootProps: Any = Unit, container: ServiceContainer = ServiceContainer()) =
    runApp(microDom, Element(rootType, rootProps), container)
