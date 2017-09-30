package kogul.drawing

import sdl2cairo.pango.*
import sdl2cairo.SWIGTYPE_p__PangoContext

class Pango(val context: SWIGTYPE_p__PangoContext) {
    constructor() : this(pango_context_new())
}
