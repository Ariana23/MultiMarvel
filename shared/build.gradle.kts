import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
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
           // implementation("com.appmattus.crypto:crypto:0.1.6")
            implementation("org.bouncycastle:bcprov-jdk15on:1.70")

            implementation("com.squareup.okio:okio:3.3.0")
            implementation("com.squareup.picasso:picasso:2.8")



            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin
            api(libs.koin.core)
            // put your Multiplatform dependencies here
        }
        androidMain.dependencies {
            // Ktor
            implementation(libs.ktor.android)
            implementation("io.ktor:ktor-client-cio:2.0.0")
          // implementation("com.appmattus.crypto:crypto:0.1.6")
            implementation("org.bouncycastle:bcprov-jdk15on:1.70")

            implementation("com.squareup.okio:okio:3.3.0")
            implementation("com.squareup.picasso:picasso:2.8")



            // Koin
            api(libs.koin.android)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
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
