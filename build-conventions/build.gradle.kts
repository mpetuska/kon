@file:Suppress("OPT_IN_IS_NOT_ENABLED")

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()
}

dependencies {
  implementation("com.android.tools.build:gradle:_")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
  implementation("org.jetbrains.kotlin:kotlin-serialization:_")
  implementation("com.github.jakemarsden:git-hooks-gradle-plugin:_")
  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:_")
  implementation("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:_")
  implementation("io.github.gradle-nexus:publish-plugin:_")
  implementation("org.jetbrains.dokka:dokka-gradle-plugin:_")
  implementation("dev.petuska:klip-gradle-plugin:_")
  @OptIn(InternalRefreshVersionsApi::class)
  implementation("de.fayard.refreshVersions:refreshVersions-core:${RefreshVersionsCorePlugin.currentVersion}")
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      languageVersion = "1.4"
    }
  }
}
