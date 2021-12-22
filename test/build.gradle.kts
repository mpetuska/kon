plugins { id("plugin.library-mpp") }

description = "Local test utilities"

kotlin {
  explicitApi = null
  sourceSets {
    commonMain {
      dependencies {
        api(kotlin("test"))
        api(kotlin("test-annotations-common"))
        api("dev.petuska:klip:_")
      }
    }
    named("androidMain") { dependencies { api(kotlin("test-junit5")) } }
    named("jvmMain") { dependencies { api(kotlin("test-junit5")) } }
    named("jsMain") { dependencies { api(kotlin("test-js")) } }
  }

  val targetsWithCoroutines =
    util.KotlinTargetDetails.values()
      .filter(util.KotlinTargetDetails::hasCoroutines)
      .map(util.KotlinTargetDetails::presetName)

  targets.filter { it.preset?.name in targetsWithCoroutines }.forEach {
    (
      it.compilations.findByName("main")?.defaultSourceSet
        ?: sourceSets.findByName("${it.name}Main")
      )?.apply {
      dependencies { api("org.jetbrains.kotlinx:kotlinx-coroutines-core:_") }
    }
  }
}
