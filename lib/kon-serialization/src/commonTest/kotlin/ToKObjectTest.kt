package dev.petuska.kon

import dev.petuska.klip.api.assertKlip
import dev.petuska.kon.serialization.toKObject
import kotlin.test.Test
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.addJsonArray
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import local.test.BlockingTest

class ToKObjectTest : BlockingTest {
  @Test
  fun test() = blockingTest {
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
    kon.assertKlip()
  }
}
