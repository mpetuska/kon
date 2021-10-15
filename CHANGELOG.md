# v1.1.0
## Breaking Changes
* Removed all value setter extensions in favour of `to`. All values should be changes to use that `"myField" to "value"`
## Changes
* New `kon-serialization` interop module with two way conversions between `JsonObject` and `KON`

# v1.0.0
INITIAL RELEASE
## Changes
* kobj and karr builders for objects and arrays
* json-like DSL for fluid syntax
* toString implementation that returns properly formatted json entities for both, KObject and KArray
