package util

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget

fun <T : KotlinTarget> KotlinMultiplatformExtension.targetGroup(
  name: String,
  mainSourceSetTarget: String,
  testSourceSetTarget: String,
  vararg targets: T
): Pair<String, String> {
  val mainName = "${name}Main"
  val testName = "${name}Test"
  sourceSets {
    val main = create(mainName) { dependsOn(getByName(mainSourceSetTarget)) }
    val test = create(testName) { dependsOn(getByName(testSourceSetTarget)) }
    targets.forEach { target ->
      target.compilations["main"].defaultSourceSet { dependsOn(main) }
      target.compilations["test"].defaultSourceSet { dependsOn(test) }
    }
  }
  return mainName to testName
}

fun NamedDomainObjectContainer<KotlinSourceSet>.withName(name: String, action: Action<KotlinSourceSet>) {
  matching { it.name == name }.all(action)
}

private fun NamedDomainObjectContainer<KotlinSourceSet>.sharedSourceSets(
  sourceSets: List<String>,
  action: Action<KotlinSourceSet>,
) {
  sourceSets.forEach {
    withName(it, action)
  }
}

fun NamedDomainObjectContainer<KotlinSourceSet>.sharedMain(action: Action<KotlinSourceSet>) {
  sharedSourceSets(listOf("sharedMain", "androidMain"), action)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.sharedTest(action: Action<KotlinSourceSet>) {
  sharedSourceSets(listOf("sharedTest", "androidTest"), action)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.blockingMain(action: Action<KotlinSourceSet>) {
  sharedSourceSets(listOf("blockingMain", "androidMain"), action)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.blockingTest(action: Action<KotlinSourceSet>) {
  sharedSourceSets(listOf("blockingTest", "androidTest"), action)
}
