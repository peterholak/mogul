package mogul.react.injection

private typealias Constructor<T> = () -> T

class TypeNotInContainerException(val name: String?) : Exception("Type '$name' is missing from the service container.")

class ServiceContainer {

//    These currently don't work in Kotlin Native:
//    val objects = mutableMapOf<KClass<*>, Any?>()
//    val constructors: MutableMap<KClass<*>, Constructor<*>> = mutableMapOf()
    val taggedConstructors = mutableMapOf<String, Constructor<*>>()
    val taggedObjects = mutableMapOf<String, Any?>()

//    These currently don't work in Kotlin Native:
//    @Suppress("UNCHECKED_CAST")
//    fun <T: Any> get(cls: KClass<T>): T {
//        return objects.getOrPut(cls) { constructors[cls]?.invoke() ?: throw TypeNotInContainerException(cls.simpleName) } as T
//    }
//
//    inline fun <reified T: Any> get(): T = get(T::class)
//
//    inline fun <reified T> register(noinline code: () -> T) {
//        constructors.put(T::class, code)
//    }
//
//    inline fun <reified T> register(value: T) {
//        objects.put(T::class, value)
//    }
    inline fun <reified T> get(tag: String): T {
        return taggedObjects.getOrPut(tag) { taggedConstructors[tag]?.invoke() ?: throw TypeNotInContainerException(tag) } as T
    }

    fun register(tag: String, code: Constructor<*>) {
        taggedConstructors.put(tag, code)
    }
}

fun container(code: ServiceContainer.() -> Unit): ServiceContainer {
    val container = ServiceContainer()
    code(container)
    return container
}
