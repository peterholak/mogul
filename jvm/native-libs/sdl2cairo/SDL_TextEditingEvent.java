/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_TextEditingEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_TextEditingEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_TextEditingEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_TextEditingEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_TextEditingEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_TextEditingEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_TextEditingEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_TextEditingEvent_timestamp_get(swigCPtr, this);
  }

  public void setWindowID(long value) {
    SDL2JNI.SDL_TextEditingEvent_windowID_set(swigCPtr, this, value);
  }

  public long getWindowID() {
    return SDL2JNI.SDL_TextEditingEvent_windowID_get(swigCPtr, this);
  }

  public void setText(String value) {
    SDL2JNI.SDL_TextEditingEvent_text_set(swigCPtr, this, value);
  }

  public String getText() {
    return SDL2JNI.SDL_TextEditingEvent_text_get(swigCPtr, this);
  }

  public void setStart(int value) {
    SDL2JNI.SDL_TextEditingEvent_start_set(swigCPtr, this, value);
  }

  public int getStart() {
    return SDL2JNI.SDL_TextEditingEvent_start_get(swigCPtr, this);
  }

  public void setLength(int value) {
    SDL2JNI.SDL_TextEditingEvent_length_set(swigCPtr, this, value);
  }

  public int getLength() {
    return SDL2JNI.SDL_TextEditingEvent_length_get(swigCPtr, this);
  }

  public SDL_TextEditingEvent() {
    this(SDL2JNI.new_SDL_TextEditingEvent(), true);
  }

}
