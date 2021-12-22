plugins {
  id("plugin.library-mpp")
  id("plugin.publishing-nexus")
  id("plugin.publishing-mpp")
  if (System.getenv("CI") == null) {
    id("plugin.git-hooks")
  }
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

kotlin {
  sourceSets { commonMain { dependencies { api(project(":lib:kon-core")) } } }
  val serializationTargets =
    setOf(
      linuxX64(),
      linuxArm64(),
      linuxArm32Hfp(),
      iosArm32(),
      iosArm64(),
      iosX64(),
      iosSimulatorArm64(),
      watchosArm32(),
      watchosArm64(),
      watchosX86(),
      watchosX64(),
      watchosSimulatorArm64(),
      tvosArm64(),
      tvosX64(),
      tvosSimulatorArm64(),
      macosX64(),
      macosArm64(),
      mingwX86(),
      mingwX64(),
    )

  serializationTargets.map {
    it.compilations["main"].defaultSourceSet.dependencies { api(project(":lib:kon-serialization")) }
  }
}
