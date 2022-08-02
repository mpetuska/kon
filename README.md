[![Slack chat](https://img.shields.io/badge/kotlinlang-chat-green?logo=slack&style=flat-square)](https://kotlinlang.slack.com/team/UL1A5BA2X)
[![Dokka docs](https://img.shields.io/badge/docs-dokka-orange?style=flat-square&logo=kotlin)](http://mpetuska.github.io/kon)
[![Version maven-central](https://img.shields.io/maven-central/v/dev.petuska/kon?logo=apache-maven&style=flat-square)](https://mvnrepository.com/artifact/dev.petuska/kon/latest)

# Kotlin Object Notation

Lightweight kotlin MPP DSL for building JSON trees

## Setup

Just drop the dependency in your `commonMain` sourceSet

```kotlin
kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation("dev.petuska:kon:_")
      }
    }
  }
}
```

### Usage

Build the object map via `kobj` builder function. Built object overrides `Any::toString` function to return proper json
representation of the underlying structure. Currently, supported kotlin types for json output are the following:

* Map
* Array
* Collection
* KObject
* KArray
* Number
* Unsigned Number
* String
* Boolean
* null
* Pair
* Triple

```kotlin
val json: KON = kobj {
  "str" to "string"
  "number" to 420
  "boolean" to true
  "object" {
    "str" to "string"
    "number" to 1
    "pair" to (1 to null)
    "triple" to Triple(1, "2", 3)
    "boolean" to true
    "withArray" [1, "2"]
    "nested" {
      "stillGood" to true
      "nullable" to null
    }
  }
  "consistentObject" to {
    "nice?" to 69
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
  "singleIntArray".to[1]
  "singleIntArray2" to karr[1]
}
json.toString() // Will return json string
json.toJS() // Only available on js target. Will return JS Json object
val jsonObject = json.toJsonObject() // Requires kon-serialization dependency. Will return kotlinx.serialization JsonObject.
jsonObject.toKObject() // Requires kon-serialization dependency. Will return KON KObject.
```

### Modules

* [kon](./) - wrapper module bringing all the other modules as transitive dependencies
* [kon-core](./lib/kon-core) - core implementation module
* [kon-serialization](./lib/kon-serialization) - kotlinx-serialization interop module
