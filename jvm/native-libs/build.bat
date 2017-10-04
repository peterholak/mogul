swig ^
    -Ic:/bin/msys64/mingw64/include ^
    -Ic:/bin/msys64/mingw64/include/SDL2 ^
    -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/sdl_wrap.c sdl.i
swig ^
    -Ic:/bin/msys64/mingw64/include ^
    -Ic:/bin/msys64/mingw64/include/pango-1.0 ^
    -Ic:/bin/msys64/mingw64/lib/glib-2.0/include ^
    -Ic:/bin/msys64/mingw64/include/glib-2.0 ^
    -Ic:/bin/msys64/mingw64/include/cairo ^
    -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/pangocairo_wrap.c pango-cairo-glib.i

gcc ^
    -shared ^
    -Ic:/lib/jdk/include ^
    -Ic:/lib/jdk/include/win32 ^
    -Ic:/bin/msys64/mingw64/include/SDL2 ^
    -o dll/sdl_wrap.dll sdl2cairo/sdl_wrap.c -lSDL2
gcc ^
    -Wno-deprecated-declarations ^
    -shared ^
    -Ic:/lib/jdk/include ^
    -Ic:/lib/jdk/include/win32 ^
    -Ic:/bin/msys64/mingw64/include/pango-1.0 ^
    -Ic:/bin/msys64/mingw64/lib/glib-2.0/include ^
    -Ic:/bin/msys64/mingw64/include/glib-2.0 ^
    -Ic:/bin/msys64/mingw64/include/cairo ^
    -o dll/pango_cairo_glib_wrap.dll sdl2cairo/pango_cairo_glib_wrap.c ^
    -lpango-1.0 -lgthread-2.0 -lgobject-2.0 -lffi -lglib-2.0 -lintl -lpcre -lintl -liconv -lpcre -lcairo -lpixman-1 -lpangocairo-1.0
