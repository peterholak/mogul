/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GParamSpecInt {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GParamSpecInt(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GParamSpecInt obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GParamSpecInt(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setParent_instance(_GParamSpec value) {
    pangoJNI._GParamSpecInt_parent_instance_set(swigCPtr, this, _GParamSpec.getCPtr(value), value);
  }

  public _GParamSpec getParent_instance() {
    long cPtr = pangoJNI._GParamSpecInt_parent_instance_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GParamSpec(cPtr, false);
  }

  public void setMinimum(int value) {
    pangoJNI._GParamSpecInt_minimum_set(swigCPtr, this, value);
  }

  public int getMinimum() {
    return pangoJNI._GParamSpecInt_minimum_get(swigCPtr, this);
  }

  public void setMaximum(int value) {
    pangoJNI._GParamSpecInt_maximum_set(swigCPtr, this, value);
  }

  public int getMaximum() {
    return pangoJNI._GParamSpecInt_maximum_get(swigCPtr, this);
  }

  public void setDefault_value(int value) {
    pangoJNI._GParamSpecInt_default_value_set(swigCPtr, this, value);
  }

  public int getDefault_value() {
    return pangoJNI._GParamSpecInt_default_value_get(swigCPtr, this);
  }

  public _GParamSpecInt() {
    this(pangoJNI.new__GParamSpecInt(), true);
  }

}