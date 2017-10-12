%module SDL2
%include "cpointer.i"
%{
    #include <SDL.h>

#ifdef SWIG
%pointer_functions(void*,voidpp)
%pointer_functions(int,intp)
%pointer_functions(double,doublep)
%pointer_cast(void*, unsigned char*, voidp_to_ucharp)
#endif
%}

%pointer_functions(void*,voidpp)
%pointer_functions(int,intp)
%pointer_functions(double,doublep)
%pointer_cast(void*, unsigned char*, voidp_to_ucharp)

typedef int SDL_bool;
typedef int Sint32;
typedef unsigned int Uint32;
typedef unsigned short Uint16;
typedef unsigned char Uint8;
typedef unsigned long long Uint64;

%include <SDL2/begin_code.h>
%include <SDL2/SDL.h>
%include <SDL2/SDL_video.h>
%include <SDL2/SDL_events.h>
%include <SDL2/SDL_render.h>
%include <SDL2/SDL_pixels.h>
%include <SDL2/SDL_mouse.h>
%include <SDL2/SDL_timer.h>