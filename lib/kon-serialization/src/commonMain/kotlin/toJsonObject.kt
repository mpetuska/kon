package dev.petuska.kon.serialization

import dev.petuska.kon.KON
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

/**
 * Converts a given [KON] to kotlinx-serialization [JsonObject]
 * @receiver kon object to convert
 * @return converted [JsonObject]
 */
public fun KON.toJsonObject(): JsonObject = buildJsonObject {
  entries.forEach { (k, v) ->
    put(k, v.toJsonElement())
  }
}

private fun Any?.toJsonElement(): JsonElement = when (this) {
  this == null -> JsonNull
  is Boolean? -> JsonPrimitive(this)
  is String? -> JsonPrimitive(this)
  is Number? -> JsonPrimitive(this)
  is Array<*> -> buildJsonArray {
    forEach {
      add(it.toJsonElement())
    }
  }
  is Collection<*> -> buildJsonArray {
    forEach {
      add(it.toJsonElement())
    }
  }
  is Pair<*, *> -> buildJsonArray {
    add(first.toJsonElement())
    add(second.toJsonElement())
  }
  is Triple<*, *, *> -> buildJsonArray {
    add(first.toJsonElement())
    add(second.toJsonElement())
    add(third.toJsonElement())
  }
  is Map<*, *> -> buildJsonObject {
    entries.forEach { (k, v) ->
      put(k.toString(), v.toJsonElement())
    }
  }
  else -> error("${this!!::class} is not a valid JsonObject element")
}
