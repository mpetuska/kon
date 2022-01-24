plugins {
  id("de.fayard.refreshVersions") version "0.40.0"
  id("com.gradle.enterprise") version "3.8"
}

rootProject.name = "kon"

include(":test")

include(
  ":lib:kon-core",
  ":lib:kon-serialization",
)

refreshVersions { extraArtifactVersionKeyRules(rootDir.resolve("versions.rules")) }
