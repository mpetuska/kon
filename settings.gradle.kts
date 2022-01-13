plugins {
  id("de.fayard.refreshVersions") version "0.30.1"
  id("com.gradle.enterprise") version "3.8.1"
}

rootProject.name = "kon"

include(":test")

include(
  ":lib:kon-core",
  ":lib:kon-serialization",
)

refreshVersions { extraArtifactVersionKeyRules(rootDir.resolve("versions.rules")) }
