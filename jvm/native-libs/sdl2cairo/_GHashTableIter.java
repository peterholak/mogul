/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GHashTableIter {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GHashTableIter(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GHashTableIter obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GHashTableIter(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setDummy1(SWIGTYPE_p_void value) {
    pangoJNI._GHashTableIter_dummy1_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getDummy1() {
    long cPtr = pangoJNI._GHashTableIter_dummy1_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setDummy2(SWIGTYPE_p_void value) {
    pangoJNI._GHashTableIter_dummy2_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getDummy2() {
    long cPtr = pangoJNI._GHashTableIter_dummy2_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setDummy3(SWIGTYPE_p_void value) {
    pangoJNI._GHashTableIter_dummy3_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getDummy3() {
    long cPtr = pangoJNI._GHashTableIter_dummy3_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setDummy4(int value) {
    pangoJNI._GHashTableIter_dummy4_set(swigCPtr, this, value);
  }

  public int getDummy4() {
    return pangoJNI._GHashTableIter_dummy4_get(swigCPtr, this);
  }

  public void setDummy5(int value) {
    pangoJNI._GHashTableIter_dummy5_set(swigCPtr, this, value);
  }

  public int getDummy5() {
    return pangoJNI._GHashTableIter_dummy5_get(swigCPtr, this);
  }

  public void setDummy6(SWIGTYPE_p_void value) {
    pangoJNI._GHashTableIter_dummy6_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getDummy6() {
    long cPtr = pangoJNI._GHashTableIter_dummy6_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public _GHashTableIter() {
    this(pangoJNI.new__GHashTableIter(), true);
  }

}