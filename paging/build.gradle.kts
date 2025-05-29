plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
    alias(libs.plugins.compose.compiler)
}


group = "com.jar.internal.library.paging"
version = "0.7.2"

val frameworkName = "MultiplatformPaging"

android {
    namespace = "com.jar.internal.library.paging"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    androidTarget {
        publishLibraryVariants("release")
        publishLibraryVariantsGroupedByFlavor = true
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.jetbrains.compose)
            implementation(libs.paging.common)
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies { }

        val iosMain by creating {
            dependsOn(commonMain.get())
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

    }

}

val gprUser: String? by project
val gprKey: String? by project

publishing {
    publications {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Changejarapp/multiplatform-paging")
                credentials {
                    username = gprUser ?: System.getenv("GPR_USER")
                    password = gprKey ?: System.getenv("GPR_API_KEY")
                }
            }
        }
    }
}