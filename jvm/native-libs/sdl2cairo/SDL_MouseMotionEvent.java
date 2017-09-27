/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_MouseMotionEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_MouseMotionEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_MouseMotionEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_MouseMotionEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_MouseMotionEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_MouseMotionEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_MouseMotionEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_MouseMotionEvent_timestamp_get(swigCPtr, this);
  }

  public void setWindowID(long value) {
    SDL2JNI.SDL_MouseMotionEvent_windowID_set(swigCPtr, this, value);
  }

  public long getWindowID() {
    return SDL2JNI.SDL_MouseMotionEvent_windowID_get(swigCPtr, this);
  }

  public void setWhich(long value) {
    SDL2JNI.SDL_MouseMotionEvent_which_set(swigCPtr, this, value);
  }

  public long getWhich() {
    return SDL2JNI.SDL_MouseMotionEvent_which_get(swigCPtr, this);
  }

  public void setState(long value) {
    SDL2JNI.SDL_MouseMotionEvent_state_set(swigCPtr, this, value);
  }

  public long getState() {
    return SDL2JNI.SDL_MouseMotionEvent_state_get(swigCPtr, this);
  }

  public void setX(SWIGTYPE_p_Sint32 value) {
    SDL2JNI.SDL_MouseMotionEvent_x_set(swigCPtr, this, SWIGTYPE_p_Sint32.getCPtr(value));
  }

  public SWIGTYPE_p_Sint32 getX() {
    return new SWIGTYPE_p_Sint32(SDL2JNI.SDL_MouseMotionEvent_x_get(swigCPtr, this), true);
  }

  public void setY(SWIGTYPE_p_Sint32 value) {
    SDL2JNI.SDL_MouseMotionEvent_y_set(swigCPtr, this, SWIGTYPE_p_Sint32.getCPtr(value));
  }

  public SWIGTYPE_p_Sint32 getY() {
    return new SWIGTYPE_p_Sint32(SDL2JNI.SDL_MouseMotionEvent_y_get(swigCPtr, this), true);
  }

  public void setXrel(SWIGTYPE_p_Sint32 value) {
    SDL2JNI.SDL_MouseMotionEvent_xrel_set(swigCPtr, this, SWIGTYPE_p_Sint32.getCPtr(value));
  }

  public SWIGTYPE_p_Sint32 getXrel() {
    return new SWIGTYPE_p_Sint32(SDL2JNI.SDL_MouseMotionEvent_xrel_get(swigCPtr, this), true);
  }

  public void setYrel(SWIGTYPE_p_Sint32 value) {
    SDL2JNI.SDL_MouseMotionEvent_yrel_set(swigCPtr, this, SWIGTYPE_p_Sint32.getCPtr(value));
  }

  public SWIGTYPE_p_Sint32 getYrel() {
    return new SWIGTYPE_p_Sint32(SDL2JNI.SDL_MouseMotionEvent_yrel_get(swigCPtr, this), true);
  }

  public SDL_MouseMotionEvent() {
    this(SDL2JNI.new_SDL_MouseMotionEvent(), true);
  }

}
