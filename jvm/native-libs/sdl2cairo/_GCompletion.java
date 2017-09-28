/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GCompletion {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GCompletion(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GCompletion obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GCompletion(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setItems(_GList value) {
    pangoJNI._GCompletion_items_set(swigCPtr, this, _GList.getCPtr(value), value);
  }

  public _GList getItems() {
    long cPtr = pangoJNI._GCompletion_items_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GList(cPtr, false);
  }

  public void setFunc(SWIGTYPE_p_f_p_void__p_char value) {
    pangoJNI._GCompletion_func_set(swigCPtr, this, SWIGTYPE_p_f_p_void__p_char.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_void__p_char getFunc() {
    long cPtr = pangoJNI._GCompletion_func_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_void__p_char(cPtr, false);
  }

  public void setPrefix(String value) {
    pangoJNI._GCompletion_prefix_set(swigCPtr, this, value);
  }

  public String getPrefix() {
    return pangoJNI._GCompletion_prefix_get(swigCPtr, this);
  }

  public void setCache(_GList value) {
    pangoJNI._GCompletion_cache_set(swigCPtr, this, _GList.getCPtr(value), value);
  }

  public _GList getCache() {
    long cPtr = pangoJNI._GCompletion_cache_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GList(cPtr, false);
  }

  public void setStrncmp_func(SWIGTYPE_p_f_p_q_const__char_p_q_const__char_unsigned_long_long__int value) {
    pangoJNI._GCompletion_strncmp_func_set(swigCPtr, this, SWIGTYPE_p_f_p_q_const__char_p_q_const__char_unsigned_long_long__int.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_q_const__char_p_q_const__char_unsigned_long_long__int getStrncmp_func() {
    long cPtr = pangoJNI._GCompletion_strncmp_func_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_q_const__char_p_q_const__char_unsigned_long_long__int(cPtr, false);
  }

  public _GCompletion() {
    this(pangoJNI.new__GCompletion(), true);
  }

}
