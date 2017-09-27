package engine

data class Position(val x: Int, val y: Int)

data class Size(val width: Int, val height: Int) {
    companion object {
        val zero = Size(0, 0)
    }
    operator fun plus(other: Size) = Size(width + other.width, height + other.height)
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

    companion object {
        fun with(left: Int, top: Int, right: Int, bottom: Int) = Rectangle(
                Position(left, top),
                Position(right, bottom)
        )
    }
}

data class BoxSizes(val top: Int = 0, val right: Int = 0, val bottom: Int = 0, val left: Int = 0) {
    val topLeft; get() = Size(left, top)
    val bottomRight; get() = Size(right, bottom)

    constructor(all: Int) : this(all, all, all, all)

    infix fun and(other: BoxSizes) = BoxSizes(
            top + other.top,
            right + other.right,
            bottom + other.bottom,
            left + other.left
    )

    fun allEqual() = (top == bottom && top == left && top == right)

    companion object {
        val zero; get() = BoxSizes()
    }
}

data class BoxColors(val top: Color? = null, val right: Color? = null, val bottom: Color? = null, val left: Color? = null) {

    constructor(all: Color?) : this(all, all, all, all)

    infix fun and(other: BoxColors) = BoxColors(
            other.top ?: top,
            other.right ?: right,
            other.bottom ?: bottom,
            other.left ?: left
    )

    fun allEqual() = (top == bottom && top == left && top == right)

    companion object {
        val none; get() = BoxColors()
    }
}
