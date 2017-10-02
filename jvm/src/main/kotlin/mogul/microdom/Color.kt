package mogul.microdom

import mogul.drawing.Cairo

data class Color(val r: Double, val g: Double, val b: Double) {
    constructor(r: Int, g: Int, b: Int) : this(r / 255.0, g / 255.0, b / 255.0)
    override fun toString() = "rgb($r255,$g255,$b255)"

    val r255; get() = (r * 255.0).toInt()
    val g255; get() = (g * 255.0).toInt()
    val b255; get() = (b * 255.0).toInt()

    companion object {
        val white = 0xFFFFFF.color
        val black = 0.color
    }
}

val Int.color
    get() = Color(this and 0xFF0000 shr 16, this and 0x00FF00 shr 8, this and 0x0000FF)

fun Cairo.setSourceRgb(color: Color) = this.setSourceRgb(color.r, color.g, color.b)
