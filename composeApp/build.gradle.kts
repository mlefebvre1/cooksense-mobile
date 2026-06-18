import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version "2.0.21" // add this
    kotlin("native.cocoapods") // add this
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "CookSense Shared Module"
        homepage = "https://example.com"
        version = "1.0"
        ios.deploymentTarget = "18.0"

        framework {
            baseName = "ComposeApp"
            isStatic = true
            freeCompilerArgs += listOf("-Xbinary=bundleId=org.chef.cooksense")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("com.stevdza-san:swipeable-kmp:1.0.6")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation("dev.gitlive:firebase-auth:2.4.0")
            implementation("dev.gitlive:firebase-firestore:2.4.0")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.chef.cooksense"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.chef.cooksense"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            buildConfigField("Boolean", "USE_EMULATOR", "true")
        }
        create("staging") {
            dimension = "env"
            buildConfigField("Boolean", "USE_EMULATOR", "false")
        }
        create("prod") {
            dimension = "env"
            buildConfigField("Boolean", "USE_EMULATOR", "false")
        }
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.14.0"))

//    implementation("com.google.firebase:firebase-analytics")

    // Firebase authentification
    implementation("dev.gitlive:firebase-auth:2.4.0")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries
}

