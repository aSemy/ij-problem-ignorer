plugins {
  kotlin("jvm") version "2.0.0"
  id("org.jetbrains.intellij.platform")
}

version = "0.0.1"
description = "Stop IntelliJ from bugging me with annoying, incessant warnings that never affect me."

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")
    instrumentationTools()
  }
}

kotlin {
  jvmToolchain(17)
}

tasks.processResources {
  val version: Provider<String> = provider { project.version.toString() }
  inputs.property("version", version)
  val description: Provider<String> = provider { project.description }
  inputs.property("description", description)

  doFirst {
    eachFile {
      if (file.name == "plugin.xml") {
        expand(
          "version" to version.get(),
          "description" to description.get(),
        )
      }
    }
  }
}
