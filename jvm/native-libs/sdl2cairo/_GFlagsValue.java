/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GFlagsValue {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GFlagsValue(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GFlagsValue obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GFlagsValue(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setValue(long value) {
    pangoJNI._GFlagsValue_value_set(swigCPtr, this, value);
  }

  public long getValue() {
    return pangoJNI._GFlagsValue_value_get(swigCPtr, this);
  }

  public void setValue_name(String value) {
    pangoJNI._GFlagsValue_value_name_set(swigCPtr, this, value);
  }

  public String getValue_name() {
    return pangoJNI._GFlagsValue_value_name_get(swigCPtr, this);
  }

  public void setValue_nick(String value) {
    pangoJNI._GFlagsValue_value_nick_set(swigCPtr, this, value);
  }

  public String getValue_nick() {
    return pangoJNI._GFlagsValue_value_nick_get(swigCPtr, this);
  }

  public _GFlagsValue() {
    this(pangoJNI.new__GFlagsValue(), true);
  }

}
