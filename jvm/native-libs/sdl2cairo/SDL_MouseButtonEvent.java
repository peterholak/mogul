/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_MouseButtonEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_MouseButtonEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_MouseButtonEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_MouseButtonEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_MouseButtonEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_MouseButtonEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_MouseButtonEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_MouseButtonEvent_timestamp_get(swigCPtr, this);
  }

  public void setWindowID(long value) {
    SDL2JNI.SDL_MouseButtonEvent_windowID_set(swigCPtr, this, value);
  }

  public long getWindowID() {
    return SDL2JNI.SDL_MouseButtonEvent_windowID_get(swigCPtr, this);
  }

  public void setWhich(long value) {
    SDL2JNI.SDL_MouseButtonEvent_which_set(swigCPtr, this, value);
  }

  public long getWhich() {
    return SDL2JNI.SDL_MouseButtonEvent_which_get(swigCPtr, this);
  }

  public void setButton(short value) {
    SDL2JNI.SDL_MouseButtonEvent_button_set(swigCPtr, this, value);
  }

  public short getButton() {
    return SDL2JNI.SDL_MouseButtonEvent_button_get(swigCPtr, this);
  }

  public void setState(short value) {
    SDL2JNI.SDL_MouseButtonEvent_state_set(swigCPtr, this, value);
  }

  public short getState() {
    return SDL2JNI.SDL_MouseButtonEvent_state_get(swigCPtr, this);
  }

  public void setClicks(short value) {
    SDL2JNI.SDL_MouseButtonEvent_clicks_set(swigCPtr, this, value);
  }

  public short getClicks() {
    return SDL2JNI.SDL_MouseButtonEvent_clicks_get(swigCPtr, this);
  }

  public void setPadding1(short value) {
    SDL2JNI.SDL_MouseButtonEvent_padding1_set(swigCPtr, this, value);
  }

  public short getPadding1() {
    return SDL2JNI.SDL_MouseButtonEvent_padding1_get(swigCPtr, this);
  }

  public void setX(int value) {
    SDL2JNI.SDL_MouseButtonEvent_x_set(swigCPtr, this, value);
  }

  public int getX() {
    return SDL2JNI.SDL_MouseButtonEvent_x_get(swigCPtr, this);
  }

  public void setY(int value) {
    SDL2JNI.SDL_MouseButtonEvent_y_set(swigCPtr, this, value);
  }

  public int getY() {
    return SDL2JNI.SDL_MouseButtonEvent_y_get(swigCPtr, this);
  }

  public SDL_MouseButtonEvent() {
    this(SDL2JNI.new_SDL_MouseButtonEvent(), true);
  }

}
