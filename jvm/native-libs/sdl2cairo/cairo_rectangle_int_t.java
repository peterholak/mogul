/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class cairo_rectangle_int_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected cairo_rectangle_int_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(cairo_rectangle_int_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        cairoJNI.delete_cairo_rectangle_int_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setX(int value) {
    cairoJNI.cairo_rectangle_int_t_x_set(swigCPtr, this, value);
  }

  public int getX() {
    return cairoJNI.cairo_rectangle_int_t_x_get(swigCPtr, this);
  }

  public void setY(int value) {
    cairoJNI.cairo_rectangle_int_t_y_set(swigCPtr, this, value);
  }

  public int getY() {
    return cairoJNI.cairo_rectangle_int_t_y_get(swigCPtr, this);
  }

  public void setWidth(int value) {
    cairoJNI.cairo_rectangle_int_t_width_set(swigCPtr, this, value);
  }

  public int getWidth() {
    return cairoJNI.cairo_rectangle_int_t_width_get(swigCPtr, this);
  }

  public void setHeight(int value) {
    cairoJNI.cairo_rectangle_int_t_height_set(swigCPtr, this, value);
  }

  public int getHeight() {
    return cairoJNI.cairo_rectangle_int_t_height_get(swigCPtr, this);
  }

  public cairo_rectangle_int_t() {
    this(cairoJNI.new_cairo_rectangle_int_t(), true);
  }

}
