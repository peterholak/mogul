/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GTypeInstance {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GTypeInstance(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GTypeInstance obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GTypeInstance(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setG_class(_GTypeClass value) {
    pangoJNI._GTypeInstance_g_class_set(swigCPtr, this, _GTypeClass.getCPtr(value), value);
  }

  public _GTypeClass getG_class() {
    long cPtr = pangoJNI._GTypeInstance_g_class_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GTypeClass(cPtr, false);
  }

  public _GTypeInstance() {
    this(pangoJNI.new__GTypeInstance(), true);
  }

}
