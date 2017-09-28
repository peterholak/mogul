/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_CommonEvent {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_CommonEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_CommonEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_CommonEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setType(long value) {
    SDL2JNI.SDL_CommonEvent_type_set(swigCPtr, this, value);
  }

  public long getType() {
    return SDL2JNI.SDL_CommonEvent_type_get(swigCPtr, this);
  }

  public void setTimestamp(long value) {
    SDL2JNI.SDL_CommonEvent_timestamp_set(swigCPtr, this, value);
  }

  public long getTimestamp() {
    return SDL2JNI.SDL_CommonEvent_timestamp_get(swigCPtr, this);
  }

  public SDL_CommonEvent() {
    this(SDL2JNI.new_SDL_CommonEvent(), true);
  }

}
