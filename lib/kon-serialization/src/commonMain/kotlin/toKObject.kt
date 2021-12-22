package dev.petuska.kon.serialization

import dev.petuska.kon.core.KObject
import dev.petuska.kon.core.karr
import dev.petuska.kon.core.kobj
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull

/**
 * Converts a given kotlinx-serialization [JsonObject] to [KObject]
 * @receiver kotlinx-serialization [JsonObject] to convert
 * @return converted [KObject]
 */
public fun JsonObject.toKObject(): KObject = kobj {
  this@toKObject.entries.forEach { (k, v) -> k to v.toKElement() }
}

private fun JsonElement?.toKElement(): Any? =
  when (this) {
    is JsonNull? -> null
    is JsonPrimitive -> {
      if (this.isString) {
        this.contentOrNull
      } else {
        booleanOrNull
          ?: contentOrNull?.let {
            if (it.contains(".")) {
              doubleOrNull
            } else {
              intOrNull
            }
          }
      }
    }
    is JsonArray -> karr(items = map(JsonElement?::toKElement).toTypedArray())
    is JsonObject -> kobj { this@toKElement.entries.forEach { (k, v) -> k to v.toKElement() } }
    else -> error("${this!!::class} is not a valid KON element")
  }
