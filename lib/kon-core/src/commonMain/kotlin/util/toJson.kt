package dev.petuska.kon.util

@Suppress("NOTHING_TO_INLINE") private inline fun Any?.toJsonString(): String = """"$this""""

@PublishedApi
internal fun Any?.toJson(): String =
    when (this) {
      is Number?, is Boolean? -> "$this"
      is String -> toJsonString()
      is Pair<*, *> -> """[${first.toJson()},${second.toJson()}]"""
      is Triple<*, *, *> -> """[${first.toJson()},${second.toJson()},${third.toJson()}]"""
      is Array<*> -> joinToString(separator = ",", prefix = "[", postfix = "]") { it.toJson() }
      is Collection<*> -> joinToString(separator = ",", prefix = "[", postfix = "]") { it.toJson() }
      is Map<*, *> ->
          entries.joinToString(separator = ",", prefix = "{", postfix = "}") { (k, v) ->
            """${k.toJsonString()}:${v.toJson()}"""
          }
      else -> {
        this?.let {
          println(
              "WARNING: ${it::class.simpleName} is not recognised JSON type. KON will serialize it as JSON string")
        }
        toJsonString()
      }
    }
