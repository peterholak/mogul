/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GSource {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GSource(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GSource obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GSource(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCallback_data(SWIGTYPE_p_void value) {
    pangoJNI._GSource_callback_data_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getCallback_data() {
    long cPtr = pangoJNI._GSource_callback_data_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setCallback_funcs(_GSourceCallbackFuncs value) {
    pangoJNI._GSource_callback_funcs_set(swigCPtr, this, _GSourceCallbackFuncs.getCPtr(value), value);
  }

  public _GSourceCallbackFuncs getCallback_funcs() {
    long cPtr = pangoJNI._GSource_callback_funcs_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSourceCallbackFuncs(cPtr, false);
  }

  public void setSource_funcs(_GSourceFuncs value) {
    pangoJNI._GSource_source_funcs_set(swigCPtr, this, _GSourceFuncs.getCPtr(value), value);
  }

  public _GSourceFuncs getSource_funcs() {
    long cPtr = pangoJNI._GSource_source_funcs_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSourceFuncs(cPtr, false);
  }

  public void setRef_count(long value) {
    pangoJNI._GSource_ref_count_set(swigCPtr, this, value);
  }

  public long getRef_count() {
    return pangoJNI._GSource_ref_count_get(swigCPtr, this);
  }

  public void setContext(SWIGTYPE_p__GMainContext value) {
    pangoJNI._GSource_context_set(swigCPtr, this, SWIGTYPE_p__GMainContext.getCPtr(value));
  }

  public SWIGTYPE_p__GMainContext getContext() {
    long cPtr = pangoJNI._GSource_context_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p__GMainContext(cPtr, false);
  }

  public void setPriority(int value) {
    pangoJNI._GSource_priority_set(swigCPtr, this, value);
  }

  public int getPriority() {
    return pangoJNI._GSource_priority_get(swigCPtr, this);
  }

  public void setFlags(long value) {
    pangoJNI._GSource_flags_set(swigCPtr, this, value);
  }

  public long getFlags() {
    return pangoJNI._GSource_flags_get(swigCPtr, this);
  }

  public void setSource_id(long value) {
    pangoJNI._GSource_source_id_set(swigCPtr, this, value);
  }

  public long getSource_id() {
    return pangoJNI._GSource_source_id_get(swigCPtr, this);
  }

  public void setPoll_fds(_GSList value) {
    pangoJNI._GSource_poll_fds_set(swigCPtr, this, _GSList.getCPtr(value), value);
  }

  public _GSList getPoll_fds() {
    long cPtr = pangoJNI._GSource_poll_fds_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSList(cPtr, false);
  }

  public void setPrev(_GSource value) {
    pangoJNI._GSource_prev_set(swigCPtr, this, _GSource.getCPtr(value), value);
  }

  public _GSource getPrev() {
    long cPtr = pangoJNI._GSource_prev_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSource(cPtr, false);
  }

  public void setNext(_GSource value) {
    pangoJNI._GSource_next_set(swigCPtr, this, _GSource.getCPtr(value), value);
  }

  public _GSource getNext() {
    long cPtr = pangoJNI._GSource_next_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSource(cPtr, false);
  }

  public void setName(String value) {
    pangoJNI._GSource_name_set(swigCPtr, this, value);
  }

  public String getName() {
    return pangoJNI._GSource_name_get(swigCPtr, this);
  }

  public void setPriv(SWIGTYPE_p__GSourcePrivate value) {
    pangoJNI._GSource_priv_set(swigCPtr, this, SWIGTYPE_p__GSourcePrivate.getCPtr(value));
  }

  public SWIGTYPE_p__GSourcePrivate getPriv() {
    long cPtr = pangoJNI._GSource_priv_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p__GSourcePrivate(cPtr, false);
  }

  public _GSource() {
    this(pangoJNI.new__GSource(), true);
  }

}