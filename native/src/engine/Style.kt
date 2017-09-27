package engine

class StyleAttribute(val inherited: Boolean = false)

// Doing it this way instead of annotations, because Kotlin Native doesn't support much reflection atm.
val StyleMetadata = mapOf(
        "color" to StyleAttribute(true)
)

@Suppress("unused")
@MicroDomMarker
class Style private constructor(private val map: MutableMap<String, Any?>) {
    val asMap: Map<String, Any?> = map

    constructor() : this(mutableMapOf<String, Any?>().withDefault { null })
    operator fun plus(other: Style) = Style(LinkedHashMap(map).apply { putAll(other.map) })
    override fun toString() = "Style:$map"

    var color: Color? by map
    var backgroundColor: Color? by map
    var borderColor: Color? by map
    var margin: BoxSizes? by map
    var padding: BoxSizes? by map
    var width: Int? by map
    var height: Int? by map
    var zIndex: Int? by map
    var fontSize: Double? by map
}

fun style(code: Style.() -> Unit): Style {
    val s = Style()
    code(s)
    return s
}
