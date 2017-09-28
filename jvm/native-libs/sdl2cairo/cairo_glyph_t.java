/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class cairo_glyph_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected cairo_glyph_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(cairo_glyph_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        cairoJNI.delete_cairo_glyph_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setIndex(long value) {
    cairoJNI.cairo_glyph_t_index_set(swigCPtr, this, value);
  }

  public long getIndex() {
    return cairoJNI.cairo_glyph_t_index_get(swigCPtr, this);
  }

  public void setX(double value) {
    cairoJNI.cairo_glyph_t_x_set(swigCPtr, this, value);
  }

  public double getX() {
    return cairoJNI.cairo_glyph_t_x_get(swigCPtr, this);
  }

  public void setY(double value) {
    cairoJNI.cairo_glyph_t_y_set(swigCPtr, this, value);
  }

  public double getY() {
    return cairoJNI.cairo_glyph_t_y_get(swigCPtr, this);
  }

  public cairo_glyph_t() {
    this(cairoJNI.new_cairo_glyph_t(), true);
  }

}
