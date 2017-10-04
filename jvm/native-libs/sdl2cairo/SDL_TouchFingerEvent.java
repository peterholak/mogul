/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_TouchFingerEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_TouchFingerEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_TouchFingerEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_TouchFingerEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_TouchFingerEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_TouchFingerEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_TouchFingerEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_TouchFingerEvent_timestamp_get(swigCPtr, this);
  }

  public void setTouchId(SWIGTYPE_p_SDL_TouchID value) {
    SDL2JNI.SDL_TouchFingerEvent_touchId_set(swigCPtr, this, SWIGTYPE_p_SDL_TouchID.getCPtr(value));
  }

  public SWIGTYPE_p_SDL_TouchID getTouchId() {
    return new SWIGTYPE_p_SDL_TouchID(SDL2JNI.SDL_TouchFingerEvent_touchId_get(swigCPtr, this), true);
  }

  public void setFingerId(SWIGTYPE_p_SDL_FingerID value) {
    SDL2JNI.SDL_TouchFingerEvent_fingerId_set(swigCPtr, this, SWIGTYPE_p_SDL_FingerID.getCPtr(value));
  }

  public SWIGTYPE_p_SDL_FingerID getFingerId() {
    return new SWIGTYPE_p_SDL_FingerID(SDL2JNI.SDL_TouchFingerEvent_fingerId_get(swigCPtr, this), true);
  }

  public void setX(float value) {
    SDL2JNI.SDL_TouchFingerEvent_x_set(swigCPtr, this, value);
  }

  public float getX() {
    return SDL2JNI.SDL_TouchFingerEvent_x_get(swigCPtr, this);
  }

  public void setY(float value) {
    SDL2JNI.SDL_TouchFingerEvent_y_set(swigCPtr, this, value);
  }

  public float getY() {
    return SDL2JNI.SDL_TouchFingerEvent_y_get(swigCPtr, this);
  }

  public void setDx(float value) {
    SDL2JNI.SDL_TouchFingerEvent_dx_set(swigCPtr, this, value);
  }

  public float getDx() {
    return SDL2JNI.SDL_TouchFingerEvent_dx_get(swigCPtr, this);
  }

  public void setDy(float value) {
    SDL2JNI.SDL_TouchFingerEvent_dy_set(swigCPtr, this, value);
  }

  public float getDy() {
    return SDL2JNI.SDL_TouchFingerEvent_dy_get(swigCPtr, this);
  }

  public void setPressure(float value) {
    SDL2JNI.SDL_TouchFingerEvent_pressure_set(swigCPtr, this, value);
  }

  public float getPressure() {
    return SDL2JNI.SDL_TouchFingerEvent_pressure_get(swigCPtr, this);
  }

  public SDL_TouchFingerEvent() {
    this(SDL2JNI.new_SDL_TouchFingerEvent(), true);
  }

}
