/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class SDL_PixelFormat {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SDL_PixelFormat(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_PixelFormat obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SDL2JNI.delete_SDL_PixelFormat(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setFormat(long value) {
    SDL2JNI.SDL_PixelFormat_format_set(swigCPtr, this, value);
  }

  public long getFormat() {
    return SDL2JNI.SDL_PixelFormat_format_get(swigCPtr, this);
  }

  public void setPalette(SDL_Palette value) {
    SDL2JNI.SDL_PixelFormat_palette_set(swigCPtr, this, SDL_Palette.getCPtr(value), value);
  }

  public SDL_Palette getPalette() {
    long cPtr = SDL2JNI.SDL_PixelFormat_palette_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SDL_Palette(cPtr, false);
  }

  public void setBitsPerPixel(short value) {
    SDL2JNI.SDL_PixelFormat_BitsPerPixel_set(swigCPtr, this, value);
  }

  public short getBitsPerPixel() {
    return SDL2JNI.SDL_PixelFormat_BitsPerPixel_get(swigCPtr, this);
  }

  public void setBytesPerPixel(short value) {
    SDL2JNI.SDL_PixelFormat_BytesPerPixel_set(swigCPtr, this, value);
  }

  public short getBytesPerPixel() {
    return SDL2JNI.SDL_PixelFormat_BytesPerPixel_get(swigCPtr, this);
  }

  public void setPadding(SWIGTYPE_p_unsigned_char value) {
    SDL2JNI.SDL_PixelFormat_padding_set(swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(value));
  }

  public SWIGTYPE_p_unsigned_char getPadding() {
    long cPtr = SDL2JNI.SDL_PixelFormat_padding_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_char(cPtr, false);
  }

  public void setRmask(long value) {
    SDL2JNI.SDL_PixelFormat_Rmask_set(swigCPtr, this, value);
  }

  public long getRmask() {
    return SDL2JNI.SDL_PixelFormat_Rmask_get(swigCPtr, this);
  }

  public void setGmask(long value) {
    SDL2JNI.SDL_PixelFormat_Gmask_set(swigCPtr, this, value);
  }

  public long getGmask() {
    return SDL2JNI.SDL_PixelFormat_Gmask_get(swigCPtr, this);
  }

  public void setBmask(long value) {
    SDL2JNI.SDL_PixelFormat_Bmask_set(swigCPtr, this, value);
  }

  public long getBmask() {
    return SDL2JNI.SDL_PixelFormat_Bmask_get(swigCPtr, this);
  }

  public void setAmask(long value) {
    SDL2JNI.SDL_PixelFormat_Amask_set(swigCPtr, this, value);
  }

  public long getAmask() {
    return SDL2JNI.SDL_PixelFormat_Amask_get(swigCPtr, this);
  }

  public void setRloss(short value) {
    SDL2JNI.SDL_PixelFormat_Rloss_set(swigCPtr, this, value);
  }

  public short getRloss() {
    return SDL2JNI.SDL_PixelFormat_Rloss_get(swigCPtr, this);
  }

  public void setGloss(short value) {
    SDL2JNI.SDL_PixelFormat_Gloss_set(swigCPtr, this, value);
  }

  public short getGloss() {
    return SDL2JNI.SDL_PixelFormat_Gloss_get(swigCPtr, this);
  }

  public void setBloss(short value) {
    SDL2JNI.SDL_PixelFormat_Bloss_set(swigCPtr, this, value);
  }

  public short getBloss() {
    return SDL2JNI.SDL_PixelFormat_Bloss_get(swigCPtr, this);
  }

  public void setAloss(short value) {
    SDL2JNI.SDL_PixelFormat_Aloss_set(swigCPtr, this, value);
  }

  public short getAloss() {
    return SDL2JNI.SDL_PixelFormat_Aloss_get(swigCPtr, this);
  }

  public void setRshift(short value) {
    SDL2JNI.SDL_PixelFormat_Rshift_set(swigCPtr, this, value);
  }

  public short getRshift() {
    return SDL2JNI.SDL_PixelFormat_Rshift_get(swigCPtr, this);
  }

  public void setGshift(short value) {
    SDL2JNI.SDL_PixelFormat_Gshift_set(swigCPtr, this, value);
  }

  public short getGshift() {
    return SDL2JNI.SDL_PixelFormat_Gshift_get(swigCPtr, this);
  }

  public void setBshift(short value) {
    SDL2JNI.SDL_PixelFormat_Bshift_set(swigCPtr, this, value);
  }

  public short getBshift() {
    return SDL2JNI.SDL_PixelFormat_Bshift_get(swigCPtr, this);
  }

  public void setAshift(short value) {
    SDL2JNI.SDL_PixelFormat_Ashift_set(swigCPtr, this, value);
  }

  public short getAshift() {
    return SDL2JNI.SDL_PixelFormat_Ashift_get(swigCPtr, this);
  }

  public void setRefcount(int value) {
    SDL2JNI.SDL_PixelFormat_refcount_set(swigCPtr, this, value);
  }

  public int getRefcount() {
    return SDL2JNI.SDL_PixelFormat_refcount_get(swigCPtr, this);
  }

  public void setNext(SDL_PixelFormat value) {
    SDL2JNI.SDL_PixelFormat_next_set(swigCPtr, this, SDL_PixelFormat.getCPtr(value), value);
  }

  public SDL_PixelFormat getNext() {
    long cPtr = SDL2JNI.SDL_PixelFormat_next_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SDL_PixelFormat(cPtr, false);
  }

  public SDL_PixelFormat() {
    this(SDL2JNI.new_SDL_PixelFormat(), true);
  }

}
