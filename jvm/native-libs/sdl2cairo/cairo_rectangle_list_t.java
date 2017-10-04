/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class cairo_rectangle_list_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected cairo_rectangle_list_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(cairo_rectangle_list_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete_cairo_rectangle_list_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setStatus(cairo_status_t value) {
    pangoJNI.cairo_rectangle_list_t_status_set(swigCPtr, this, value.swigValue());
  }

  public cairo_status_t getStatus() {
    return cairo_status_t.swigToEnum(pangoJNI.cairo_rectangle_list_t_status_get(swigCPtr, this));
  }

  public void setRectangles(cairo_rectangle_t value) {
    pangoJNI.cairo_rectangle_list_t_rectangles_set(swigCPtr, this, cairo_rectangle_t.getCPtr(value), value);
  }

  public cairo_rectangle_t getRectangles() {
    long cPtr = pangoJNI.cairo_rectangle_list_t_rectangles_get(swigCPtr, this);
    return (cPtr == 0) ? null : new cairo_rectangle_t(cPtr, false);
  }

  public void setNum_rectangles(int value) {
    pangoJNI.cairo_rectangle_list_t_num_rectangles_set(swigCPtr, this, value);
  }

  public int getNum_rectangles() {
    return pangoJNI.cairo_rectangle_list_t_num_rectangles_get(swigCPtr, this);
  }

  public cairo_rectangle_list_t() {
    this(pangoJNI.new_cairo_rectangle_list_t(), true);
  }

}
