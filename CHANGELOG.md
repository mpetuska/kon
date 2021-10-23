# v1.1.3
## Build Versions
* JVM: 11
* Gradle: 7.2
* Kotlin: 1.5.31
## Breaking Changes
* All `kon-core` module code moved one package up from `dev.petuska.kon` to `dev.petuska.kon.core`
## Changes
* Android support (somehow I've missed it initially...)

# v1.1.2
## Build Versions
* JVM: 11
* Gradle: 7.2
* Kotlin: 1.5.31
## Changes
* Typesafe versions of `KON` and `KObject` to improve extensibility and allow restricting value types.

# v1.1.1
## Changes
* New safe array setter `"key".to[1]` that works with single int arrays
* New object setter `"key" to {}` to allow consistent usage with value keys 
* New helper `KON::toJS: Json` to convert KON objects to plain JS objects on js sourceSets
* `karr` array builder moved out of `KObject` interface
* unsigned number support for stringification

# v1.1.0
## Breaking Changes
* Removed all value setter extensions in favour of `to`. All values should be changes to use that `"myField" to "value"`
## Changes
* New `kon-serialization` interop module with two-way conversions between `JsonObject` and `KON`

# v1.0.0
INITIAL RELEASE
## Changes
* kobj and karr builders for objects and arrays
* json-like DSL for fluid syntax
* toString implementation that returns properly formatted json entities for both, KObject and KArray
