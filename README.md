[![Dokka docs](https://img.shields.io/badge/docs-dokka-orange?style=flat-square)](http://mpetuska.github.io/kon)
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
* List
* KObject
* KArray
* Number
* String
* Boolean
* null
* Pair
* Triple

```kotlin
val json: ObjectMap = kobj {
  "str" by "string"
  "number"..1
  "boolean" % true
  "object" {
    "str" += "string"
    "pair" to (1 to "2")
    "triple" by Triple(true, false, null)
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
json.toString() // will return json string
```

### Modules

* [kon](./) - wrapper module
* [kon-core](./lib/kon-core) - core implementation module
