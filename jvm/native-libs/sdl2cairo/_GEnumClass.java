/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GEnumClass {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GEnumClass(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GEnumClass obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GEnumClass(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setG_type_class(_GTypeClass value) {
    pangoJNI._GEnumClass_g_type_class_set(swigCPtr, this, _GTypeClass.getCPtr(value), value);
  }

  public _GTypeClass getG_type_class() {
    long cPtr = pangoJNI._GEnumClass_g_type_class_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GTypeClass(cPtr, false);
  }

  public void setMinimum(int value) {
    pangoJNI._GEnumClass_minimum_set(swigCPtr, this, value);
  }

  public int getMinimum() {
    return pangoJNI._GEnumClass_minimum_get(swigCPtr, this);
  }

  public void setMaximum(int value) {
    pangoJNI._GEnumClass_maximum_set(swigCPtr, this, value);
  }

  public int getMaximum() {
    return pangoJNI._GEnumClass_maximum_get(swigCPtr, this);
  }

  public void setN_values(long value) {
    pangoJNI._GEnumClass_n_values_set(swigCPtr, this, value);
  }

  public long getN_values() {
    return pangoJNI._GEnumClass_n_values_get(swigCPtr, this);
  }

  public void setValues(_GEnumValue value) {
    pangoJNI._GEnumClass_values_set(swigCPtr, this, _GEnumValue.getCPtr(value), value);
  }

  public _GEnumValue getValues() {
    long cPtr = pangoJNI._GEnumClass_values_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GEnumValue(cPtr, false);
  }

  public _GEnumClass() {
    this(pangoJNI.new__GEnumClass(), true);
  }

}
