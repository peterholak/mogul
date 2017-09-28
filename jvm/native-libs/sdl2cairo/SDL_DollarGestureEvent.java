/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_DollarGestureEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_DollarGestureEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_DollarGestureEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_DollarGestureEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_DollarGestureEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_DollarGestureEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_DollarGestureEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_DollarGestureEvent_timestamp_get(swigCPtr, this);
  }

  public void setTouchId(SWIGTYPE_p_SDL_TouchID value) {
    SDL2JNI.SDL_DollarGestureEvent_touchId_set(swigCPtr, this, SWIGTYPE_p_SDL_TouchID.getCPtr(value));
  }

  public SWIGTYPE_p_SDL_TouchID getTouchId() {
    return new SWIGTYPE_p_SDL_TouchID(SDL2JNI.SDL_DollarGestureEvent_touchId_get(swigCPtr, this), true);
  }

  public void setGestureId(SWIGTYPE_p_SDL_GestureID value) {
    SDL2JNI.SDL_DollarGestureEvent_gestureId_set(swigCPtr, this, SWIGTYPE_p_SDL_GestureID.getCPtr(value));
  }

  public SWIGTYPE_p_SDL_GestureID getGestureId() {
    return new SWIGTYPE_p_SDL_GestureID(SDL2JNI.SDL_DollarGestureEvent_gestureId_get(swigCPtr, this), true);
  }

  public void setNumFingers(long value) {
    SDL2JNI.SDL_DollarGestureEvent_numFingers_set(swigCPtr, this, value);
  }

  public long getNumFingers() {
    return SDL2JNI.SDL_DollarGestureEvent_numFingers_get(swigCPtr, this);
  }

  public void setError(float value) {
    SDL2JNI.SDL_DollarGestureEvent_error_set(swigCPtr, this, value);
  }

  public float getError() {
    return SDL2JNI.SDL_DollarGestureEvent_error_get(swigCPtr, this);
  }

  public void setX(float value) {
    SDL2JNI.SDL_DollarGestureEvent_x_set(swigCPtr, this, value);
  }

  public float getX() {
    return SDL2JNI.SDL_DollarGestureEvent_x_get(swigCPtr, this);
  }

  public void setY(float value) {
    SDL2JNI.SDL_DollarGestureEvent_y_set(swigCPtr, this, value);
  }

  public float getY() {
    return SDL2JNI.SDL_DollarGestureEvent_y_get(swigCPtr, this);
  }

  public SDL_DollarGestureEvent() {
    this(SDL2JNI.new_SDL_DollarGestureEvent(), true);
  }

}
