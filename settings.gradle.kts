import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform


rootProject.name = "ij-problem-ignorer"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}


plugins {
  id("org.jetbrains.intellij.platform.settings") version "2.0.0"
}


@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode = RepositoriesMode.PREFER_SETTINGS

  repositories {
    mavenCentral()

    intellijPlatform {
      defaultRepositories()
    }
  }
}
