/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GStaticPrivate {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GStaticPrivate(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GStaticPrivate obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GStaticPrivate(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setIndex(long value) {
    pangoJNI._GStaticPrivate_index_set(swigCPtr, this, value);
  }

  public long getIndex() {
    return pangoJNI._GStaticPrivate_index_get(swigCPtr, this);
  }

  public _GStaticPrivate() {
    this(pangoJNI.new__GStaticPrivate(), true);
  }

}