plugins {
  id("de.fayard.refreshVersions") version "0.30.0"
  id("com.gradle.enterprise") version "3.7.2"
}

rootProject.name = "kon"

include(":test")

include(
    ":lib:kon-core",
    ":lib:kon-serialization",
)

refreshVersions { extraArtifactVersionKeyRules(rootDir.resolve("versions.rules")) }
