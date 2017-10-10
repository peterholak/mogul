package mogul.platform

/**
 * What happens when the window is closed in a system way (e.g. close button in title bar).
 * This will be used to determine how the window should behave in case the application doesn't
 * specify it (e.g. no `onClose` event handlers, no `hidden` props are set, etc.).
 */
enum class AutoClose {

    /** The window will not close, an event will be dispatched and the app can handle it itself. */
    Manual,

    /**
     * The window will be destroyed and removed from the list of windows.
     * If anything changes in this window's subtree, it will be re-created.
     */
    Close,

    /**
     * The window will be destroyed and removed from the list of windows.
     * Any changes to the VDOM tree under this window will simply be no-ops.
     * Lifecycle events and operations utilizing refs will not be run.
     *
     * TODO: this is pretty stupid overall, in the future, window lifecycle can be handled
     * differently, such as having an `isOpen` prop, and not just relying on simple presence/absence
     * of the window element.
     */
    CloseAndDoNotRecreate
}

interface Window {
    fun render()
    fun wasInvalidated(): Boolean
    fun invalidate()
    fun draw(code: (cairo: Cairo) -> Unit)
}