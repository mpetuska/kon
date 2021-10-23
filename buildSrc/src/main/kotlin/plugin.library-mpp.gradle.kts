import util.nativeTargetGroup

plugins {
  kotlin("multiplatform")
  id("plugin.common")
  id("plugin.library-common")
  id("dev.petuska.klip")
  id("com.android.library")
}

android {
  compileSdkVersion(31)
  defaultConfig {
    minSdkVersion(1)
    targetSdkVersion(31)
  }
}

kotlin {
  android()
  jvm()
  js {
    useCommonJs()
    nodejs()
  }

  nativeTargetGroup(
      "androidNdk",
      androidNativeArm32(),
      androidNativeArm64(),
  )

  nativeTargetGroup(
      "linux",
      linuxX64(),
      linuxMips32(),
      linuxMipsel32(),
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
