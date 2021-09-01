plugins {
  kotlin("jvm")
  kotlin("kapt")
  kotlin("plugin.serialization")
  `maven-publish`
}

val kotlinVersion: String by project
val velocityVersion: String by project

group = "com.velocitypowered"
version = "$velocityVersion+$kotlinVersion"

repositories {
  mavenLocal()
  mavenCentral()

  maven("https://nexus.velocitypowered.com/repository/maven-public/")
}

dependencies {
  api(kotlin("reflect"))
  api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
  api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion")
  api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinVersion")
  api("net.kyori:adventure-extra-kotlin:4.7.0")

  compileOnly("com.velocitypowered:velocity-api:$velocityVersion")
  kapt("com.velocitypowered:velocity-api:$velocityVersion")
}

tasks {
  register<Jar>("fatJar") {
    duplicatesStrategy = DuplicatesStrategy.WARN
    archiveClassifier.set("fat")
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(sourceSets.main.get().output)
  }
  
  build {
    dependsOn("fatJar")
  }
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = "com.github.PureOrigins"
      artifactId = project.name
      version = "$velocityVersion+$kotlinVersion"
      
      artifact(tasks["jar"])
      artifact(tasks["kotlinSourcesJar"])
    }
  }
}

