/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GPtrArray {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GPtrArray(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GPtrArray obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GPtrArray(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setPdata(SWIGTYPE_p_p_void value) {
    pangoJNI._GPtrArray_pdata_set(swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_p_void getPdata() {
    long cPtr = pangoJNI._GPtrArray_pdata_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_p_void(cPtr, false);
  }

  public void setLen(long value) {
    pangoJNI._GPtrArray_len_set(swigCPtr, this, value);
  }

  public long getLen() {
    return pangoJNI._GPtrArray_len_get(swigCPtr, this);
  }

  public _GPtrArray() {
    this(pangoJNI.new__GPtrArray(), true);
  }

}
