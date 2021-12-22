import org.gradle.kotlin.dsl.invoke
import util.nativeTargetGroup

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("plugin.common")
  id("plugin.library-common")
  id("dev.petuska.klip")
}

kotlin {
  sourceSets {
    commonMain { dependencies { api("org.jetbrains.kotlinx:kotlinx-serialization-json:_") } }
  }
  jvm()
  js {
    useCommonJs()
    nodejs()
  }

  nativeTargetGroup(
    "linux",
    linuxX64(),
    linuxArm64(),
    linuxArm32Hfp(),
  )

  nativeTargetGroup(
    "ios",
    iosArm32(),
    iosArm64(),
    iosX64(),
    iosSimulatorArm64(),
  )

  nativeTargetGroup(
    "watchos",
    watchosArm32(),
    watchosArm64(),
    watchosX86(),
    watchosX64(),
    watchosSimulatorArm64(),
  )

  nativeTargetGroup(
    "tvos",
    tvosArm64(),
    tvosX64(),
    tvosSimulatorArm64(),
  )

  nativeTargetGroup(
    "macos",
    macosX64(),
    macosArm64(),
  )

  nativeTargetGroup(
    "mingw",
    mingwX86(),
    mingwX64(),
  )
}
