import org.jetbrains.kotlin.konan.target.HostManager

plugins {
  id("io.gitlab.arturbosch.detekt")
  id("convention.local-properties")
  id("convention.detekt")
  idea
}

repositories {
  mavenCentral()
  google()
  gradlePluginPortal()
}

printlnCI(
  """
  CI: $CI
  SANDBOX: $SANDBOX
  isMainHost: $isMainHost
  ---
  hostIsLinux: ${HostManager.hostIsLinux}
  hostIsMac: ${HostManager.hostIsMac}
  hostIsMingw: ${HostManager.hostIsMingw}
  """.trimIndent()
)

idea {
  module {
    isDownloadSources = true
    isDownloadJavadoc = true
  }
}

tasks {
  withType<Test> { useJUnitPlatform() }
}

afterEvaluate {
  tasks {
    if (findByName("compile") == null) {
      register("compile") {
        dependsOn(withType(AbstractCompile::class))
        group = "build"
      }
    }
    if (findByName("allTests") == null) {
      register("allTests") {
        dependsOn(withType(AbstractTestTask::class))
        group = "verification"
      }
    }
  }
}
