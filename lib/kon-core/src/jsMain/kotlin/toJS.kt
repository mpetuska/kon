package dev.petuska.kon.core

import kotlin.js.Json
import kotlin.js.json

/**
 * Converts a given [KON] to JavaScript [Json]
 * @receiver kon object to convert
 * @return converted [Json]
 */
public fun KON.toJS(): Json {
  return json(pairs = entries.map { (k, v) -> k to v.toJSElement() }.toTypedArray())
}

private fun Any?.toJSElement(): Any? =
    when (this) {
      is Long -> toInt()
      is Boolean?, is String?, is Number? -> this
      is Array<*> -> Array(size) { this[it].toJSElement() }
      is Collection<*> -> map { it.toJSElement() }.toTypedArray()
      is Pair<*, *> -> arrayOf(first.toJSElement(), second.toJSElement())
      is Triple<*, *, *> -> arrayOf(first.toJSElement(), second.toJSElement(), third.toJSElement())
      is Map<*, *> ->
          json(pairs = entries.map { (k, v) -> k.toString() to v.toJSElement() }.toTypedArray())
      else -> error("${this!!::class} is not a valid Json element")
    }
