plugins {
  id("plugin.library-mpp")
  id("plugin.publishing-mpp")
}

description = "KON interop with kotlinx-serialization"

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:_")
      }
    }
  }
}
