plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.vanniktech.mavenPublish)
  alias(libs.plugins.dokka)
}

group = "com.infiniteretry.snizzors"
version = "1.0.0-cmp1.10-alpha01"

kotlin {
  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "Snizzors"
      isStatic = true
    }
  }

  sourceSets {
    iosMain.dependencies {
      api(compose.ui)
    }
  }
}

mavenPublishing {
  publishToMavenCentral()

  signAllPublications()

  coordinates(group.toString(), "snizzors", version.toString())

  pom {
    name = "Snizzors"
    description = "A Compose Multiplatform library enabling transparent UIKit content."
    inceptionYear = "2025"
    url = "https://github.com/Infinite-Retry/Snizzors"
    licenses {
      license {
        name = "The Apache License, Version 2.0"
        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
      }
    }
    developers {
      developer {
        id = "Infinite-Retry"
        name = "Infinite Retry Labs"
        url = "https://infiniteretry.com"
      }
    }
    scm {
      url = "https://github.com/Infinite-Retry/Snizzors"
      connection = "scm:git:git://github.com/Infinite-Retry/Snizzors.git"
      developerConnection = "scm:git:ssh://github.com/Infinite-Retry/Snizzors.git"
    }
  }
}
