rootProject.name = "buildSrc"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://www.jetbrains.com/intellij-repository/snapshots") }
    maven { url = uri("https://www.jetbrains.com/intellij-repository/releases") }
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://www.jetbrains.com/intellij-repository/snapshots") }
    maven { url = uri("https://www.jetbrains.com/intellij-repository/releases") }
  }
}