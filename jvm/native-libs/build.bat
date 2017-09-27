swig -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/sdl_wrap.c sdl.i
swig -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/cairo_wrap.c cairo.i

gcc -shared -Ic:/lib/jdk/include -Ic:/lib/jdk/include/win32 -Ic:/bin/msys64/mingw64/include/SDL2 -o dll/sdl_wrap.dll sdl2cairo/sdl_wrap.c -lSDL2
gcc -shared -Ic:/lib/jdk/include -Ic:/lib/jdk/include/win32 -Ic:/bin/msys64/mingw64/include/cairo -o dll/cairo_wrap.dll sdl2cairo/cairo_wrap.c -lcairo -lpixman-1
