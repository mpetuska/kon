package dev.petuska.kon.serialization

import dev.petuska.klip.assertions.assertKlip
import dev.petuska.klip.runner.runTest
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.addJsonArray
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import kotlin.test.Test

class ToKObjectTest {
  @Test
  fun test() = runTest {
    val jsonObject: JsonObject = buildJsonObject {
      put("str", "string")
      put("number", 1)
      put("boolean", true)
      putJsonObject("object") {
        put("str", "string")
        put("number", 1)
        put("boolean", true)
        putJsonArray("pair") {
          add(1)
          add(JsonNull)
        }
        putJsonArray("triple") {
          add(1)
          add("2")
          add(3)
        }
        putJsonArray("withArray") {
          add(1)
          add("2")
        }
        putJsonObject("nested") {
          put("stillGood", true)
          put("nullable", JsonNull)
        }
      }
      putJsonArray("array") {
        add(1)
        add("2")
        add(true)
        addJsonArray {
          add(1)
          add("2")
          add(false)
        }
        addJsonObject { put("inner", true) }
      }
    }
    val kon = jsonObject.toKObject()
    kon.toString().assertKlip()
  }
}
