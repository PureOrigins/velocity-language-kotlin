rootProject.name = "velocity-language-kotlin"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }

  plugins {
    val kotlinVersion: String by settings

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
  }
}
