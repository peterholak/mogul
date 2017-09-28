%module pango
%{
    #include <pango/pango.h>
%}

%define GLIB_VAR %inline %{ %} %enddef
%define GOBJECT_VAR %inline %{ %} %enddef
%define _GLIB_HAVE_BUILTIN_OVERFLOW_CHECKS %inline %{ %} %enddef
%ignore alloca;
%ignore g_atomic_int_get;
%ignore g_atomic_int_set;
%ignore g_atomic_int_inc;
%ignore g_atomic_int_dec_and_test;
%ignore g_atomic_int_compare_and_exchange;
%ignore g_atomic_int_add;
%ignore g_atomic_int_and;
%ignore g_atomic_int_or;
%ignore g_atomic_int_xor;
%ignore g_atomic_pointer_get;
%ignore g_atomic_pointer_set;
%ignore g_atomic_pointer_compare_and_exchange;
%ignore g_atomic_pointer_add;
%ignore g_atomic_pointer_and;
%ignore g_atomic_pointer_or;
%ignore g_atomic_pointer_xor;
%ignore g_atomic_int_exchange_and_add;
%ignore g_once_init_leave;
%ignore g_once_init_enter;
%ignore g_pointer_bit_lock;
%ignore g_pointer_bit_trylock;
%ignore g_pointer_bit_unlock;
%ignore g_steal_pointer;
%ignore g_io_channel_win32_new_stream_socket;
%ignore _g_log_fallback_handler;
%ignore _g_param_type_register_static_constant;
%ignore _g_signals_destroy;
%include "pango/pango.h"