/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GValue {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GValue(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GValue obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GValue(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setG_type(java.math.BigInteger value) {
    pangoJNI._GValue_g_type_set(swigCPtr, this, value);
  }

  public java.math.BigInteger getG_type() {
    return pangoJNI._GValue_g_type_get(swigCPtr, this);
  }

  public _GValue_data getData() {
    long cPtr = pangoJNI._GValue_data_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GValue_data(cPtr, false);
  }

  public _GValue() {
    this(pangoJNI.new__GValue(), true);
  }

}