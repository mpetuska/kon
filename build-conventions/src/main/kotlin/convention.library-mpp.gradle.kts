import gradle.kotlin.dsl.accessors._52923ef3b552a50dd5090fc76dd7e3c7.sourceSets
import util.withName

plugins {
  id("convention.mpp")
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

  sourceSets {
    named("commonTest") {
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
    withName("androidMain") {
      kotlin.srcDir("src/sharedMain/kotlin")
      kotlin.srcDir("src/blockingMain/kotlin")
      resources.srcDir("src/sharedMain/resources")
      resources.srcDir("src/blockingMain/resources")
    }
    withName("androidTest") {
      kotlin.srcDir("src/sharedTest/kotlin")
      kotlin.srcDir("src/blockingTest/kotlin")
      resources.srcDir("src/sharedTest/resources")
      resources.srcDir("src/blockingTest/resources")
      dependencies {
        implementation(kotlin("test-junit5"))
      }
    }
  }
}
