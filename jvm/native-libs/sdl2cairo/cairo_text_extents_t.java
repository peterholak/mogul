/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class cairo_text_extents_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected cairo_text_extents_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(cairo_text_extents_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete_cairo_text_extents_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setX_bearing(double value) {
    pangoJNI.cairo_text_extents_t_x_bearing_set(swigCPtr, this, value);
  }

  public double getX_bearing() {
    return pangoJNI.cairo_text_extents_t_x_bearing_get(swigCPtr, this);
  }

  public void setY_bearing(double value) {
    pangoJNI.cairo_text_extents_t_y_bearing_set(swigCPtr, this, value);
  }

  public double getY_bearing() {
    return pangoJNI.cairo_text_extents_t_y_bearing_get(swigCPtr, this);
  }

  public void setWidth(double value) {
    pangoJNI.cairo_text_extents_t_width_set(swigCPtr, this, value);
  }

  public double getWidth() {
    return pangoJNI.cairo_text_extents_t_width_get(swigCPtr, this);
  }

  public void setHeight(double value) {
    pangoJNI.cairo_text_extents_t_height_set(swigCPtr, this, value);
  }

  public double getHeight() {
    return pangoJNI.cairo_text_extents_t_height_get(swigCPtr, this);
  }

  public void setX_advance(double value) {
    pangoJNI.cairo_text_extents_t_x_advance_set(swigCPtr, this, value);
  }

  public double getX_advance() {
    return pangoJNI.cairo_text_extents_t_x_advance_get(swigCPtr, this);
  }

  public void setY_advance(double value) {
    pangoJNI.cairo_text_extents_t_y_advance_set(swigCPtr, this, value);
  }

  public double getY_advance() {
    return pangoJNI.cairo_text_extents_t_y_advance_get(swigCPtr, this);
  }

  public cairo_text_extents_t() {
    this(pangoJNI.new_cairo_text_extents_t(), true);
  }

}
