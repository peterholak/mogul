package mogul.microdom

import mogul.microdom.primitives.MicroDomMarker

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

    operator fun plus(other: Style) =
        Style(LinkedHashMap(map).withDefault { null }.apply { putAll(other.map) })

    override fun toString() = "Style:$map"

    var color: Color? by map
    var backgroundColor: Color? by map
    var border: Borders? by map
    var margin: BoxSizes? by map
    var padding: BoxSizes? by map
    var width: Int? by map
    var height: Int? by map
    var zIndex: Int? by map
    var fontSize: Number? by map

    fun bs(top: Int = 0, right: Int = 0, bottom: Int = 0, left: Int = 0) = BoxSizes(top, right, bottom, left)
    val Int.all; get() = BoxSizes(this)
    val Int.top; get() = BoxSizes(top = this)
    val Int.bottom; get() = BoxSizes(bottom = this)
    val Int.left; get() = BoxSizes(left = this)
    val Int.right; get() = BoxSizes(right = this)
    fun borders(width: Int = 0, color: Color? = null) {
        border = Borders(width, color)
    }
}

class Borders(val width: BoxSizes = BoxSizes.zero, val color: BoxColors = BoxColors.none) {
    constructor(width: Int = 0, color: Color? = null) : this(BoxSizes(width), BoxColors(color))
}

fun style(code: Style.() -> Unit): Style {
    val s = Style()
    code(s)
    return s
}
