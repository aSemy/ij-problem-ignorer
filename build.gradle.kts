plugins {
  kotlin("jvm") version "2.0.0"
  id("org.jetbrains.intellij.platform")
}

description = "Suppress IntelliJ warning icon"
project.version = object {
  private val gitVersion = project.gitVersion
  override fun toString(): String = gitVersion.get()
}


dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2024.2")
    instrumentationTools()
  }
}

intellijPlatform {
  pluginConfiguration {
    id = "dev.adamko.problemIgnorer"
    name = "Problem Ignorer"
    description = """
      |Tired of seeing that persistent warning symbol in IntelliJ?
      |Problem Ignorer is here to help.
      |
      |This plugin disables the problems icon caused by irrelevant bugs and third-party plugin,
      |allowing you to focus on what really matters: your code.
      |
      |No more unnecessary distractions; just a cleaner, more efficient development environment.
      |
      |Key Features:
      |
      | - Suppresses the error icon.
      | - You can control the visibility of the icon per project.
      | - Eliminates irrelevant warnings that don’t affect your workflow.
      | - Simple and lightweight, designed for seamless integration with IntelliJ.
      | - Perfect for developers who value a streamlined workspace without the clutter of unnecessary warnings.
      |
    """.trimMargin()
    version = provider { project.version.toString() }
    ideaVersion {
      sinceBuild = "242"
      untilBuild = "242.*"
    }
  }
}

kotlin {
  jvmToolchain(21)
}
