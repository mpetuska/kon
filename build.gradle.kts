plugins {
  id("plugin.library-mpp")
  id("plugin.publishing-nexus")
  id("plugin.publishing-mpp")
  id("com.github.jakemarsden.git-hooks")
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "ktfmtFormat",
      "pre-push" to "ktfmtCheck"
    )
  )
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        subprojects.filter { it.path.startsWith(":lib:") }.forEach {
          api(it)
        }
      }
    }
  }
}
