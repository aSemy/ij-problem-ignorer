plugins {
  kotlin("jvm") version "2.0.0"
  id("org.jetbrains.intellij.platform")
}


dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")
    instrumentationTools()
  }
}

kotlin {
  jvmToolchain(17)
}
