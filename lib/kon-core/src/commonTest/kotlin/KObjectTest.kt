package dev.petuska.kon

import dev.petuska.klip.api.assertKlip
import local.test.BlockingTest
import kotlin.test.Test

class KObjectTest : BlockingTest {
  @Test
  fun test() = blockingTest {
    val json: ObjectMap = kobj {
      "str" by "string"
      "number"..1
      "boolean" % true
      "object" {
        "str" += "string"
        "number" to 1
        "pair" to (1 to null)
        "triple" to Triple(1, "2", 3)
        "boolean" += true
        "withArray"[1, "2"]
        "nested" {
          "stillGood" by true
          "nullable" by null
        }
      }
      "array"[
        1,
        "2",
        true,
        karr[1, "2", false],
        kobj {
          "inner" += true
        }
      ]
    }
    json.assertKlip()
  }
}
