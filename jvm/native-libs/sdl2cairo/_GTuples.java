/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GTuples {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GTuples(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GTuples obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GTuples(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setLen(long value) {
    pangoJNI._GTuples_len_set(swigCPtr, this, value);
  }

  public long getLen() {
    return pangoJNI._GTuples_len_get(swigCPtr, this);
  }

  public _GTuples() {
    this(pangoJNI.new__GTuples(), true);
  }

}
