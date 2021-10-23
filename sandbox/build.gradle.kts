plugins {
  kotlin("jvm") version "1.5.30"
  application
}

repositories {
  mavenCentral()
  google()
}

description = "Local consumer sandbox"

application {
  mainClass.set("local.sandbox.MainKt")
}

dependencies {
  implementation("dev.petuska:kon")
  testImplementation("dev.petuska:test")
}
