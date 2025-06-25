import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.20"
    id("app.cash.sqldelight") version "2.1.0"


}
@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    androidTarget {

    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation("io.ktor:ktor-client-cio:2.0.0")
            implementation("org.bouncycastle:bcprov-jdk15on:1.70")

            implementation("com.squareup.okio:okio:3.3.0")
            implementation("com.squareup.picasso:picasso:2.8")

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin
            api(libs.koin.core)

            implementation("app.cash.sqldelight:sqlite-driver:2.1.0")


        }
        androidMain.dependencies {
            // Ktor
            implementation(libs.ktor.android)
            implementation("io.ktor:ktor-client-cio:2.0.0")
            implementation("org.bouncycastle:bcprov-jdk15on:1.70")

            implementation("com.squareup.okio:okio:3.3.0")
            implementation("com.squareup.picasso:picasso:2.8")
            // Koin
            api(libs.koin.android)

            implementation("app.cash.sqldelight:android-driver:2.1.0")

           // implementation("app.cash.sqldelight:native-driver:2.0.0")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation("app.cash.sqldelight:native-driver:2.1.0")


        }
    }
}


sqldelight {
    databases {
        create("MarvelDatabase") {
            packageName.set("org.multimarvel")
        }
    }
}


android {
    namespace = "org.multimarvel.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
