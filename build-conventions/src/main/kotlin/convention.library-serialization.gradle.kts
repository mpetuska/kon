import org.gradle.kotlin.dsl.invoke
import util.targetGroup

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("convention.common")
  id("convention.library-android")
  id("convention.control")
  id("dev.petuska.klip")
}

kotlin {
  explicitApi()

  android {
    if (!CI || SANDBOX || isMainHost) {
      publishLibraryVariants("release", "debug")
    }
  }
  jvm()
  js {
    useCommonJs()
    browser {
      testTask {
        useKarma {
          useFirefoxHeadless()
        }
      }
    }
    nodejs()
  }

  targetGroup(
    "linux",
    "commonMain",
    "commonTest",
    linuxX64(),
    linuxArm64(),
    linuxArm32Hfp(),
  )

  targetGroup(
    "ios",
    "commonMain",
    "commonTest",
    iosArm32(),
    iosArm64(),
    iosX64(),
    iosSimulatorArm64(),
  )

  targetGroup(
    "watchos",
    "commonMain",
    "commonTest",
    watchosArm32(),
    watchosArm64(),
    watchosX86(),
    watchosX64(),
    watchosSimulatorArm64(),
  )

  targetGroup(
    "tvos",
    "commonMain",
    "commonTest",
    tvosArm64(),
    tvosX64(),
    tvosSimulatorArm64(),
  )

  targetGroup(
    "macos",
    "commonMain",
    "commonTest",
    macosX64(),
    macosArm64(),
  )

  targetGroup(
    "mingw",
    "commonMain",
    "commonTest",
    mingwX86(),
    mingwX64(),
  )

  sourceSets {
    commonMain {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:_")
      }
    }
    commonTest {
      dependencies {
        implementation(project(":test"))
      }
    }
    all {
      languageSettings {
        optIn("kotlin.RequiresOptIn")
        optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
      }
    }
  }
}
