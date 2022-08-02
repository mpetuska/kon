plugins {
  id("convention.library-serialization")
  id("convention.publishing-mpp")
}

description = "KON interop with kotlinx-serialization"

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(project(":lib:kon-core"))
      }
    }
  }
}
