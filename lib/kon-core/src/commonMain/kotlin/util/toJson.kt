package dev.petuska.kon.util

public fun Any?.toJson(): String = when (this) {
  is String -> """"$this""""
  is Pair<*, *> -> """[${first.toJson()},${second.toJson()}]"""
  is Triple<*, *, *> -> """[${first.toJson()},${second.toJson()},${third.toJson()}]"""
  is Array<*> -> joinToString(",", prefix = "[", postfix = "]") { it.toJson() }
  is List<*> -> joinToString(",", prefix = "[", postfix = "]") { it.toJson() }
  is Map<*, *> -> entries.joinToString(",", prefix = "{", postfix = "}") { (k, v) -> """"$k":${v.toJson()}""" }
  else -> "$this"
}
