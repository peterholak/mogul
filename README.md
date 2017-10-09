An experiment that could one day become a flexible GUI library for Kotlin (JVM and Native) inspired by React.

Too tired to write a proper README right now, stay tuned.

Here's a gif of fiddling around with the live reload (sped up 50%, I don't type that fast, real-time refresh delay is about a second).

![livereload](https://imgur.com/6a9df6J.gif)

The live reload currently only works on the JVM.

Here's a screenshot, as you can see, it's not practically usable at the moment, but you can already
see how the code using this library [would typically look like](native/src/mogul/demo/testComponents.kt#L16) (but better).
Right now the code is pretty hacky, but I will be cleaning that up soon. I completely avoided reflection
in order to support Kotlin Native. If it ever gets more reflection capabilities, the code could be nicer.

![screenshot](screenshot.png)

For the general idea, see https://www.reddit.com/r/programmingcirclejerk/comments/72hvwj/feat_request_add_a_loading_splash_screen_to_my/dnixh4h/

If you just want to download a binary to run it, check out the releases github tab.
