/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _PangoAttrFontFeatures {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _PangoAttrFontFeatures(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_PangoAttrFontFeatures obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__PangoAttrFontFeatures(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setAttr(_PangoAttribute value) {
    pangoJNI._PangoAttrFontFeatures_attr_set(swigCPtr, this, _PangoAttribute.getCPtr(value), value);
  }

  public _PangoAttribute getAttr() {
    long cPtr = pangoJNI._PangoAttrFontFeatures_attr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new _PangoAttribute(cPtr, false);
  }

  public void setFeatures(String value) {
    pangoJNI._PangoAttrFontFeatures_features_set(swigCPtr, this, value);
  }

  public String getFeatures() {
    return pangoJNI._PangoAttrFontFeatures_features_get(swigCPtr, this);
  }

  public _PangoAttrFontFeatures() {
    this(pangoJNI.new__PangoAttrFontFeatures(), true);
  }

}