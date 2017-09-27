#!/bin/sh

swig -I/usr/include -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/sdl_wrap.c sdl.i
swig -I/usr/include -java -package sdl2cairo -outdir sdl2cairo -o sdl2cairo/cairo_wrap.c cairo.i

gcc -shared -fPIC -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux `sdl2-config --cflags` -o dll/libsdl_wrap.so sdl2cairo/sdl_wrap.c `sdl2-config --libs`
gcc -shared -fPIC -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux `pkg-config cairo --cflags` -o dll/libcairo_wrap.so sdl2cairo/cairo_wrap.c `pkg-config cairo --libs`
