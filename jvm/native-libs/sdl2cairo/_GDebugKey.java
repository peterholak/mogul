/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GDebugKey {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GDebugKey(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GDebugKey obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GDebugKey(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setKey(String value) {
    pangoJNI._GDebugKey_key_set(swigCPtr, this, value);
  }

  public String getKey() {
    return pangoJNI._GDebugKey_key_get(swigCPtr, this);
  }

  public void setValue(long value) {
    pangoJNI._GDebugKey_value_set(swigCPtr, this, value);
  }

  public long getValue() {
    return pangoJNI._GDebugKey_value_get(swigCPtr, this);
  }

  public _GDebugKey() {
    this(pangoJNI.new__GDebugKey(), true);
  }

}