package mogul.demo

import mogul.microdom.primitives.VerticalDirection
import mogul.microdom.style
import mogul.platform.MouseEvent
import mogul.react.slow.*
import mogul.react.slow.dom.appKgx
import mogul.react.slow.dom.layoutBox
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds.*
import java.nio.file.WatchService
import kotlin.concurrent.thread


class LiveReloadDemo : StatefulComponent<Nothing, Nothing?>() {

    override val initialState: Nothing? = null
    var loader = LiveClassLoader()
    var type: ElementType = dynamicElementType()
    var watchServiceRunning = false

    override fun render() = appKgx {
        window(title="Live reload demo", width=500, height=500, root = kgx{

            layoutBox(style = style{ margin = 20.top }, spacing = 10, direction = VerticalDirection) {
                button("Force reload", onClick = this@LiveReloadDemo::onReloadClicked)
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

    fun reload() {
        loader = LiveClassLoader()
        val newVersion = loader.loadClass("mogul.demo.LiveComponent")
        type = dynamicElementType()
        setState(null)

    }

    fun dynamicElementType(): ElementType {
        return ElementType("LiveComponent", {
            @Suppress("UNCHECKED_CAST")
            loader.loadClass("mogul.demo.LiveComponent").newInstance() as Component<Any>
        })
    }

    fun startWatchService() {
        val watcher = FileSystems.getDefault().newWatchService()
        val path = Paths.get("build/kotlin-classes/main/mogul/demo/")
        path.register(watcher, ENTRY_MODIFY)

        thread(name="LiveReload file watcher") {
            // The file gets written twice on each change, we only want to read after every second write.
            var ignoreReload = true
            while(true) {
                val key = watcher.take()
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
                            Thread.sleep(200)
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
        if (name?.startsWith("mogul.demo.LiveComponent") != true) {
            return super.loadClass(name)
        }
        return findClass(name)
    }

    override fun findClass(name: String?): Class<*> {
        if (name == null) return super.findClass(name)
        if (customCache.contains(name)) {
            return customCache[name]!!
        }

        val shortName = name.substring("mogul.demo.".length)
        val fileName = "build/kotlin-classes/main/mogul/demo/$shortName.class"
        val bytes = File(fileName).readBytes()
        val cls = defineClass(name, bytes, 0, bytes.size)
        customCache.put(name, cls)
        return cls
    }
}

val liveReloadType = ElementType("LiveReload", { LiveReloadDemo() })
