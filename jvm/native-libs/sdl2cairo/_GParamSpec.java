/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GParamSpec {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GParamSpec(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GParamSpec obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GParamSpec(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setG_type_instance(_GTypeInstance value) {
    pangoJNI._GParamSpec_g_type_instance_set(swigCPtr, this, _GTypeInstance.getCPtr(value), value);
  }

  public _GTypeInstance getG_type_instance() {
    long cPtr = pangoJNI._GParamSpec_g_type_instance_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _GTypeInstance(cPtr, false);
  }

  public void setName(String value) {
    pangoJNI._GParamSpec_name_set(swigCPtr, this, value);
  }

  public String getName() {
    return pangoJNI._GParamSpec_name_get(swigCPtr, this);
  }

  public void setFlags(GParamFlags value) {
    pangoJNI._GParamSpec_flags_set(swigCPtr, this, value.swigValue());
  }

  public GParamFlags getFlags() {
    return GParamFlags.swigToEnum(pangoJNI._GParamSpec_flags_get(swigCPtr, this));
  }

  public void setValue_type(java.math.BigInteger value) {
    pangoJNI._GParamSpec_value_type_set(swigCPtr, this, value);
  }

  public java.math.BigInteger getValue_type() {
    return pangoJNI._GParamSpec_value_type_get(swigCPtr, this);
  }

  public void setOwner_type(java.math.BigInteger value) {
    pangoJNI._GParamSpec_owner_type_set(swigCPtr, this, value);
  }

  public java.math.BigInteger getOwner_type() {
    return pangoJNI._GParamSpec_owner_type_get(swigCPtr, this);
  }

  public void set_nick(String value) {
    pangoJNI._GParamSpec__nick_set(swigCPtr, this, value);
  }

  public String get_nick() {
    return pangoJNI._GParamSpec__nick_get(swigCPtr, this);
  }

  public void set_blurb(String value) {
    pangoJNI._GParamSpec__blurb_set(swigCPtr, this, value);
  }

  public String get_blurb() {
    return pangoJNI._GParamSpec__blurb_get(swigCPtr, this);
  }

  public void setQdata(SWIGTYPE_p__GData value) {
    pangoJNI._GParamSpec_qdata_set(swigCPtr, this, SWIGTYPE_p__GData.getCPtr(value));
  }

  public SWIGTYPE_p__GData getQdata() {
    long cPtr = pangoJNI._GParamSpec_qdata_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p__GData(cPtr, false);
  }

  public void setRef_count(long value) {
    pangoJNI._GParamSpec_ref_count_set(swigCPtr, this, value);
  }

  public long getRef_count() {
    return pangoJNI._GParamSpec_ref_count_get(swigCPtr, this);
  }

  public void setParam_id(long value) {
    pangoJNI._GParamSpec_param_id_set(swigCPtr, this, value);
  }

  public long getParam_id() {
    return pangoJNI._GParamSpec_param_id_get(swigCPtr, this);
  }

  public _GParamSpec() {
    this(pangoJNI.new__GParamSpec(), true);
  }

}