/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GTypeClass {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GTypeClass(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GTypeClass obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GTypeClass(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setG_type(java.math.BigInteger value) {
    pangoJNI._GTypeClass_g_type_set(swigCPtr, this, value);
  }

  public java.math.BigInteger getG_type() {
    return pangoJNI._GTypeClass_g_type_get(swigCPtr, this);
  }

  public _GTypeClass() {
    this(pangoJNI.new__GTypeClass(), true);
  }

}
