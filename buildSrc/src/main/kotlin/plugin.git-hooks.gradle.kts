plugins {
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
