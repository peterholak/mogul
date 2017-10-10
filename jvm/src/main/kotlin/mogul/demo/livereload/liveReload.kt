package mogul.demo.livereload

import mogul.demo.button
import mogul.microdom.primitives.VerticalDirection
import mogul.microdom.style
import mogul.platform.Engine
import mogul.platform.MouseEvent
import mogul.react.*
import mogul.react.dom.appGui
import mogul.react.dom.layoutBox
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY
import java.nio.file.StandardWatchEventKinds.OVERFLOW
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

data class LiveReloadProps(val engine: Engine)
@Suppress("UNUSED_PARAMETER", "unused")
class LiveReloadDemo : StatefulComponent<LiveReloadProps, LiveReloadDemo.State>() {

    data class State(val nonLiveCounter: Int)
    override val initialState = State(nonLiveCounter = 0)
    var type: ElementType = dynamicElementType()

    var watchServiceRunning = false

    override fun render() = appGui {
        window(title = "Live reload demo", width = 500, height = 500, root = gui {

            layoutBox(style = style { margin = 20.top }, spacing = 10, direction = VerticalDirection) {
                layoutBox(spacing = 5) {
                    button("Force reload", onClick = this@LiveReloadDemo::onReloadClicked)
                    -"Non-live counter: ${state.nonLiveCounter}"
                    button("Increment", onClick = this@LiveReloadDemo::increment)
                }
                children.add(Element(type, Unit))
            }

        })
    }

    fun onReloadClicked(event: MouseEvent) {
        if (!watchServiceRunning) {
            startWatchService()
            watchServiceRunning = true
        }
        reload()
    }

    fun increment(event: MouseEvent) {
        setState(state.copy(nonLiveCounter = state.nonLiveCounter + 1))
    }

    fun reload() {
        type = dynamicElementType()
        forceUpdate()
    }

    fun dynamicElementType(): ElementType {
        return ElementType("LiveComponent", {
            @Suppress("UNCHECKED_CAST")
            LiveClassLoader().loadClass("mogul.demo.livereload.LiveComponent").newInstance() as Component<Any>
        })
    }

    fun startWatchService() {
        val watcher = FileSystems.getDefault().newWatchService()
        val path = Paths.get("build/kotlin-classes/main/mogul/demo/livereload/")
        path.register(watcher, ENTRY_MODIFY)

        thread(name="LiveReload file watcher") {
            // The file gets written twice on each change, we only want to read after every second write.
            var ignoreReload = true
            while(!props.engine.quitting()) {
                val key = watcher.poll(1000, TimeUnit.MILLISECONDS) ?: continue
                key.pollEvents().forEach {
                    val kind = it.kind()
                    if (kind == OVERFLOW) {
                        return@forEach
                    }

                    val filename = it.context() as Path
                    println(filename)
                    if (filename.toString() == "LiveComponent.class") {
                        if (!ignoreReload) {
                            // TODO: solve this cleanly
                            Thread.sleep(100)
                            reload()
                        }
                        ignoreReload = !ignoreReload
                    }
                }
                val valid = key.reset()
                if (!valid) {
                    break
                }
            }
        }
    }

}

class LiveClassLoader : ClassLoader() {
    val customCache: MutableMap<String, Class<*>> = mutableMapOf()

    override fun loadClass(name: String?): Class<*> {
        if (name?.startsWith("mogul.demo.livereload.LiveComponent") != true) {
            return super.loadClass(name)
        }
        return findClass(name)
    }

    override fun findClass(name: String?): Class<*> {
        if (name == null) return super.findClass(name)
        if (customCache.contains(name)) {
            return customCache[name]!!
        }

        val shortName = name.substring("mogul.demo.livereload.".length)
        val fileName = "build/kotlin-classes/main/mogul/demo/livereload/$shortName.class"
        val bytes = File(fileName).readBytes()
        val cls = defineClass(name, bytes, 0, bytes.size)
        customCache.put(name, cls)
        return cls
    }
}

@Suppress("unused")
val liveReloadType = ElementType("LiveReload", { LiveReloadDemo() })
