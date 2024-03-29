package dev.petuska.kon.serialization

import dev.petuska.klip.assertions.assertKlip
import dev.petuska.klip.runner.runTest
import dev.petuska.kon.core.KON
import dev.petuska.kon.core.kobj
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ToJsonObjectTest {
  @Test
  fun test() = runTest {
    val kon: KON = kobj {
      "str" to "string"
      "number" to 1
      "boolean" to true
      "object" {
        "str" to "string"
        "number" to 1
        "pair" to (1 to null)
        "triple" to Triple(1, "2", 3)
        "boolean" to true
        "withArray"[1, "2"]
        "nested" {
          "stillGood" to true
          "nullable" to null
        }
      }
      "array"[1, "2", true, karr[1, "2", false], kobj { "inner" to true }]
    }
    val jsonObject = kon.toJsonObject()
    Json.encodeToString(jsonObject).assertKlip()
  }
}
