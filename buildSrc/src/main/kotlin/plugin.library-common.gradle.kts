import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.target.HostManager
import util.buildHost

plugins {
  kotlin("multiplatform")
}

kotlin {
  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      dependencies {
        implementation(project(":test"))
      }
    }
    create("nativeMain") {
      dependsOn(commonMain)
    }
    create("nativeTest") {
      dependsOn(commonTest)
    }
  }

  explicitApi()
}

tasks {
  project.properties["org.gradle.project.targetCompatibility"]?.toString()?.let {
    withType<KotlinCompile> {
      kotlinOptions {
        jvmTarget = it
      }
    }
  }
  withType<CInteropProcess> {
    onlyIf {
      konanTarget.buildHost == HostManager.host.family
    }
  }
  withType<AbstractKotlinNativeCompile<*, *>> {
    onlyIf {
      compilation.konanTarget.buildHost == HostManager.host.family
    }
  }
}
