plugins {
  id("convention.publishing")
}

val mainHostSpec: Spec<in Task> = Spec { !CI || SANDBOX || isMainHost }

afterEvaluate {
  publishing {
    publications {
      all {
        val targetPublication = this@all
        tasks.withType<AbstractPublishToMaven>()
          .matching { it.publication == targetPublication }
          .configureEach {
            onlyIf(mainHostSpec)
          }
        tasks.withType<GenerateModuleMetadata>()
          .matching { it.publication.get() == targetPublication }
          .configureEach {
            onlyIf(mainHostSpec)
          }
      }
    }
  }
}
