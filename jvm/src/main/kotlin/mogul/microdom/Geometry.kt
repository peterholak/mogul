package mogul.microdom

import mogul.platform.XY

data class Position(val x: Int, val y: Int)
val XY.position; get() = Position(x.toInt(), y.toInt())

data class Size(val width: Int, val height: Int) {
    companion object {
        val zero = Size(0, 0)
    }
    operator fun plus(other: Size?) = Size(width + (other?.width ?: 0), height + (other?.height ?: 0))
    operator fun plus(edgeSizes: EdgeSizes?) = this + (edgeSizes?.topLeft ?: zero) + (edgeSizes?.bottomRight ?: zero)
    operator fun minus(other: Size?) = Size(width - (other?.width ?: 0), height - (other?.height ?: 0))
    operator fun minus(edgeSizes: EdgeSizes?) = this - (edgeSizes?.topLeft ?: zero) - (edgeSizes?.bottomRight ?: zero)
    operator fun div(by: Int) = Size(width / by, height / by)
}

data class Rectangle(val topLeft: Position, val size: Size) {
    val left; get() = topLeft.x
    val top; get() = topLeft.y
    val width; get() = size.width
    val height; get() = size.height
    val bottom; get() = topLeft.y + size.height
    val right; get() = topLeft.x + size.width
    val bottomRight; get() = Position(bottom, right)

    constructor(left: Int, top: Int, width: Int, height: Int) : this(Position(left, top), Size(width, height))
    constructor(topLeft: Position, bottomRight: Position) :
            this(topLeft, Size(bottomRight.x - topLeft.x, bottomRight.y - topLeft.y))

    fun overlaps(other: Rectangle) =
        left <= other.right && right >= other.left && top <= other.bottom && bottom >= other.top

    fun contains(point: Position) =
        point.x in left..right && point.y in top..bottom

    companion object {
        fun with(left: Int, top: Int, right: Int, bottom: Int) = Rectangle(
                Position(left, top),
                Position(right, bottom)
        )
    }
}

data class EdgeSizes(val top: Int = 0, val right: Int = 0, val bottom: Int = 0, val left: Int = 0) {
    val topLeft; get() = Size(left, top)
    val bottomRight; get() = Size(right, bottom)

    constructor(all: Int) : this(all, all, all, all)

    infix fun and(other: EdgeSizes) = EdgeSizes(
            top + other.top,
            right + other.right,
            bottom + other.bottom,
            left + other.left
    )

    fun allEqual() = (top == bottom && top == left && top == right)

    companion object {
        val zero = EdgeSizes()
    }
}

data class EdgeColors(val top: Color? = null, val right: Color? = null, val bottom: Color? = null, val left: Color? = null) {

    constructor(all: Color?) : this(all, all, all, all)

    infix fun and(other: EdgeColors) = EdgeColors(
            other.top ?: top,
            other.right ?: right,
            other.bottom ?: bottom,
            other.left ?: left
    )

    fun allEqual() = (top == bottom && top == left && top == right)

    companion object {
        val none; get() = EdgeColors()
    }
}
