plugins {
  kotlin("jvm") version "2.0.0"
  id("org.jetbrains.intellij.platform")
}

description = "Stop IntelliJ from bugging me with annoying, incessant warnings that never affect me."
project.version = object {
  private val gitVersion = project.gitVersion
  override fun toString(): String = gitVersion.get()
}


dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2024.1.4")
    instrumentationTools()
  }
}

intellijPlatform {
  pluginConfiguration {
    id = "dev.adamko.problemIgnorer"
    name = "Problem Ignorer"
    description = provider { project.description }
    version = provider { project.version.toString() }
    ideaVersion {
      sinceBuild = "241"
      untilBuild = "241.*"
    }
  }
}

kotlin {
  jvmToolchain(17)
}
