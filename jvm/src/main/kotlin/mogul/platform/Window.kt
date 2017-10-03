package mogul.platform

interface Window {
    fun render()
    fun wasInvalidated(): Boolean
    fun invalidate()
    fun draw(code: (cairo: Cairo) -> Unit)
}