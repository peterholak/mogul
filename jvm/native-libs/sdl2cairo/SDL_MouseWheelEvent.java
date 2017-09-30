/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_MouseWheelEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_MouseWheelEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_MouseWheelEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_MouseWheelEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_MouseWheelEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_MouseWheelEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_MouseWheelEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_MouseWheelEvent_timestamp_get(swigCPtr, this);
  }

  public void setWindowID(long value) {
    SDL2JNI.SDL_MouseWheelEvent_windowID_set(swigCPtr, this, value);
  }

  public long getWindowID() {
    return SDL2JNI.SDL_MouseWheelEvent_windowID_get(swigCPtr, this);
  }

  public void setWhich(long value) {
    SDL2JNI.SDL_MouseWheelEvent_which_set(swigCPtr, this, value);
  }

  public long getWhich() {
    return SDL2JNI.SDL_MouseWheelEvent_which_get(swigCPtr, this);
  }

  public void setX(int value) {
    SDL2JNI.SDL_MouseWheelEvent_x_set(swigCPtr, this, value);
  }

  public int getX() {
    return SDL2JNI.SDL_MouseWheelEvent_x_get(swigCPtr, this);
  }

  public void setY(int value) {
    SDL2JNI.SDL_MouseWheelEvent_y_set(swigCPtr, this, value);
  }

  public int getY() {
    return SDL2JNI.SDL_MouseWheelEvent_y_get(swigCPtr, this);
  }

  public void setDirection(long value) {
    SDL2JNI.SDL_MouseWheelEvent_direction_set(swigCPtr, this, value);
  }

  public long getDirection() {
    return SDL2JNI.SDL_MouseWheelEvent_direction_get(swigCPtr, this);
  }

  public SDL_MouseWheelEvent() {
    this(SDL2JNI.new_SDL_MouseWheelEvent(), true);
  }

}
