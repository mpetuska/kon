plugins {
  id("plugin.library-serialization")
  id("plugin.publishing-mpp")
}

description = "KON interop with kotlinx-serialization"

kotlin { sourceSets { commonMain { dependencies { api(project(":lib:kon-core")) } } } }
