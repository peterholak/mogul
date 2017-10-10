package mogul.react

class Later<T>(var value: T? = null)

inline fun <reified T> InstantiatedElement.castLaterInstance() =
    (instance as Later<*>).value as T

inline fun <reified T> InstantiatedElement.populateLaterInstance(data: T): T {
    @Suppress("UNCHECKED_CAST")
    (instance as Later<T?>).value = data
    return data
}
