package dev.petuska.kon.core

import dev.petuska.klip.assertions.assertKlip
import dev.petuska.klip.runner.runTest
import kotlin.test.Test

class KObjectTest {
  @Test
  fun test() = runTest {
    val kon: KON = kobj {
      "str" to "string"
      "number" to 420
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
      "consistentObject" to { "nice?" to 69 }
      "array"[1, "2", true, karr[1, "2", false], kobj { "inner" to true }]
      "singleIntArray".to[1]
      "singleIntArray2" to karr[1]
    }
    kon.toString().assertKlip()
  }

  @Test
  fun arrays() = runTest {
    val kon = kobj {
      "a1" to (1)
      "a2".to[2]
      "a3"[3, 3]
      "a4"["4"]
      "a5"[null]
      "a6"[true]
      "a7"[false]
      "a8"[8L]
      "a9"[9u]
    }
    kon.toString().assertKlip()
  }
}
