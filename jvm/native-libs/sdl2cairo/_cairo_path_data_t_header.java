/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _cairo_path_data_t_header {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _cairo_path_data_t_header(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_cairo_path_data_t_header obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__cairo_path_data_t_header(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(cairo_path_data_type_t value) {
    pangoJNI._cairo_path_data_t_header_type_set(swigCPtr, this, value.swigValue());
  }

  public cairo_path_data_type_t getType() {
    return cairo_path_data_type_t.swigToEnum(pangoJNI._cairo_path_data_t_header_type_get(swigCPtr, this));
  }

  public void setLength(int value) {
    pangoJNI._cairo_path_data_t_header_length_set(swigCPtr, this, value);
  }

  public int getLength() {
    return pangoJNI._cairo_path_data_t_header_length_get(swigCPtr, this);
  }

  public _cairo_path_data_t_header() {
    this(pangoJNI.new__cairo_path_data_t_header(), true);
  }

}
