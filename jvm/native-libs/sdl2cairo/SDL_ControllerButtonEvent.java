/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_ControllerButtonEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_ControllerButtonEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_ControllerButtonEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_ControllerButtonEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_ControllerButtonEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_ControllerButtonEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_ControllerButtonEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_ControllerButtonEvent_timestamp_get(swigCPtr, this);
  }

  public void setWhich(SWIGTYPE_p_SDL_JoystickID value) {
    SDL2JNI.SDL_ControllerButtonEvent_which_set(swigCPtr, this, SWIGTYPE_p_SDL_JoystickID.getCPtr(value));
  }

  public SWIGTYPE_p_SDL_JoystickID getWhich() {
    return new SWIGTYPE_p_SDL_JoystickID(SDL2JNI.SDL_ControllerButtonEvent_which_get(swigCPtr, this), true);
  }

  public void setButton(short value) {
    SDL2JNI.SDL_ControllerButtonEvent_button_set(swigCPtr, this, value);
  }

  public short getButton() {
    return SDL2JNI.SDL_ControllerButtonEvent_button_get(swigCPtr, this);
  }

  public void setState(short value) {
    SDL2JNI.SDL_ControllerButtonEvent_state_set(swigCPtr, this, value);
  }

  public short getState() {
    return SDL2JNI.SDL_ControllerButtonEvent_state_get(swigCPtr, this);
  }

  public void setPadding1(short value) {
    SDL2JNI.SDL_ControllerButtonEvent_padding1_set(swigCPtr, this, value);
  }

  public short getPadding1() {
    return SDL2JNI.SDL_ControllerButtonEvent_padding1_get(swigCPtr, this);
  }

  public void setPadding2(short value) {
    SDL2JNI.SDL_ControllerButtonEvent_padding2_set(swigCPtr, this, value);
  }

  public short getPadding2() {
    return SDL2JNI.SDL_ControllerButtonEvent_padding2_get(swigCPtr, this);
  }

  public SDL_ControllerButtonEvent() {
    this(SDL2JNI.new_SDL_ControllerButtonEvent(), true);
  }

}
