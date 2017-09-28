/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GQueue {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GQueue(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GQueue obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GQueue(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setHead(_GList value) {
    pangoJNI._GQueue_head_set(swigCPtr, this, _GList.getCPtr(value), value);
  }

  public _GList getHead() {
    long cPtr = pangoJNI._GQueue_head_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GList(cPtr, false);
  }

  public void setTail(_GList value) {
    pangoJNI._GQueue_tail_set(swigCPtr, this, _GList.getCPtr(value), value);
  }

  public _GList getTail() {
    long cPtr = pangoJNI._GQueue_tail_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GList(cPtr, false);
  }

  public void setLength(long value) {
    pangoJNI._GQueue_length_set(swigCPtr, this, value);
  }

  public long getLength() {
    return pangoJNI._GQueue_length_get(swigCPtr, this);
  }

  public _GQueue() {
    this(pangoJNI.new__GQueue(), true);
  }

}
