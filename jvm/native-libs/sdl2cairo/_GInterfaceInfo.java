/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GInterfaceInfo {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GInterfaceInfo(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GInterfaceInfo obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GInterfaceInfo(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setInterface_init(SWIGTYPE_p_f_p_void_p_void__void value) {
    pangoJNI._GInterfaceInfo_interface_init_set(swigCPtr, this, SWIGTYPE_p_f_p_void_p_void__void.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_void_p_void__void getInterface_init() {
    long cPtr = pangoJNI._GInterfaceInfo_interface_init_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_void_p_void__void(cPtr, false);
  }

  public void setInterface_finalize(SWIGTYPE_p_f_p_void_p_void__void value) {
    pangoJNI._GInterfaceInfo_interface_finalize_set(swigCPtr, this, SWIGTYPE_p_f_p_void_p_void__void.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_void_p_void__void getInterface_finalize() {
    long cPtr = pangoJNI._GInterfaceInfo_interface_finalize_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_void_p_void__void(cPtr, false);
  }

  public void setInterface_data(SWIGTYPE_p_void value) {
    pangoJNI._GInterfaceInfo_interface_data_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getInterface_data() {
    long cPtr = pangoJNI._GInterfaceInfo_interface_data_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public _GInterfaceInfo() {
    this(pangoJNI.new__GInterfaceInfo(), true);
  }

}
