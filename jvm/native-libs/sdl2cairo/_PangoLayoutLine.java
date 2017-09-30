/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _PangoLayoutLine {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _PangoLayoutLine(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_PangoLayoutLine obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__PangoLayoutLine(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setLayout(SWIGTYPE_p__PangoLayout value) {
    pangoJNI._PangoLayoutLine_layout_set(swigCPtr, this, SWIGTYPE_p__PangoLayout.getCPtr(value));
  }

  public SWIGTYPE_p__PangoLayout getLayout() {
    long cPtr = pangoJNI._PangoLayoutLine_layout_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p__PangoLayout(cPtr, false);
  }

  public void setStart_index(int value) {
    pangoJNI._PangoLayoutLine_start_index_set(swigCPtr, this, value);
  }

  public int getStart_index() {
    return pangoJNI._PangoLayoutLine_start_index_get(swigCPtr, this);
  }

  public void setLength(int value) {
    pangoJNI._PangoLayoutLine_length_set(swigCPtr, this, value);
  }

  public int getLength() {
    return pangoJNI._PangoLayoutLine_length_get(swigCPtr, this);
  }

  public void setRuns(_GSList value) {
    pangoJNI._PangoLayoutLine_runs_set(swigCPtr, this, _GSList.getCPtr(value), value);
  }

  public _GSList getRuns() {
    long cPtr = pangoJNI._PangoLayoutLine_runs_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GSList(cPtr, false);
  }

  public void setIs_paragraph_start(long value) {
    pangoJNI._PangoLayoutLine_is_paragraph_start_set(swigCPtr, this, value);
  }

  public long getIs_paragraph_start() {
    return pangoJNI._PangoLayoutLine_is_paragraph_start_get(swigCPtr, this);
  }

  public void setResolved_dir(long value) {
    pangoJNI._PangoLayoutLine_resolved_dir_set(swigCPtr, this, value);
  }

  public long getResolved_dir() {
    return pangoJNI._PangoLayoutLine_resolved_dir_get(swigCPtr, this);
  }

  public _PangoLayoutLine() {
    this(pangoJNI.new__PangoLayoutLine(), true);
  }

}