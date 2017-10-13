package mogul.kobx

// Stuff currently missing in Kotlin Native
public inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? = if (!predicate(this)) this else null
