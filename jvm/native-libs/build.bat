set MINGW_HOME=c:/bin/msys64/mingw64
set JDK_HOME=c:/lib/jdk

swig ^
    -I%MINGW_HOME%/include ^
    -I%MINGW_HOME%/include/SDL2 ^
    -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/sdl_wrap.c sdl.i
swig ^
    -I%MINGW_HOME%/include ^
    -I%MINGW_HOME%/include/cairo ^
    -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/cairo_wrap.c cairo.i

gcc ^
    -O2 ^
    -s ^
    -shared ^
    -I%JDK_HOME%/include ^
    -I%JDK_HOME%/include/win32 ^
    -I%MINGW_HOME%/include/SDL2 ^
    -o dll/sdl_wrap.dll sdl2cairo/sdl_wrap.c -lSDL2
gcc ^
    -O2 ^
    -s ^
    -Wno-deprecated-declarations ^
    -shared ^
    -I%JDK_HOME%/include ^
    -I%JDK_HOME%/include/win32 ^
    -I%MINGW_HOME%/include/cairo ^
    -o dll/cairo_wrap.dll sdl2cairo/cairo_wrap.c ^
    -lcairo
