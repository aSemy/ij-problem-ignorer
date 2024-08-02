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
    ideaVersion {
      sinceBuild = "241"
      untilBuild = "241.*"
    }
  }
}

kotlin {
  jvmToolchain(17)
}

tasks.processResources {
  val description: Provider<String> = provider { project.description }
  inputs.property("description", description)

  doFirst {
    eachFile {
      if (file.name == "plugin.xml") {
        expand(
          "description" to description.get(),
        )
      }
    }
  }
}
