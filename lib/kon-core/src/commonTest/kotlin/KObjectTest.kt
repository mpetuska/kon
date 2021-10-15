package dev.petuska.kon

import dev.petuska.klip.api.assertKlip
import local.test.BlockingTest
import kotlin.test.Test

class KObjectTest : BlockingTest {
  @Test
  fun test() = blockingTest {
    val json: KON = kobj {
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
      "array"[
        1,
        "2",
        true,
        karr[1, "2", false],
        kobj {
          "inner" to true
        }
      ]
    }
    json.assertKlip()
  }
}
