@file:Suppress("PropertyName")

import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    packagingOptions {
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/licenses/ASM")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }

    compileSdk = versionFor("version.android.compile.sdk").toIntOrNull()

    defaultConfig {
        minSdk = versionFor("version.android.min.sdk").toIntOrNull()
        targetSdk = versionFor("version.android.target.sdk").toIntOrNull()

        applicationId = "com.company"

        versionName = "2"
        versionCode = 2

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    dexOptions {
        preDexLibraries = true
    }

    buildTypes {
        getByName("debug") {
            isCrunchPngs = false
            isDebuggable = true

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("agent")

    productFlavors {
        register("staging") {
            setDimension("agent")

            applicationIdSuffix = ".staging"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("debug").java.srcDirs("src/debug/kotlin")
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    implementation(project(":domain"))
    implementation(Kotlin.stdlib.jdk8)

    // Kotlin
    kapt(AndroidX.annotation)
    implementation(AndroidX.fragment.ktx)
    implementation("com.google.android.material:material:_")
    // Deprecated. TODO: Stop using this artifact.
    implementation("androidx.legacy:legacy-support-v4:_")
    implementation("androidx.datastore:datastore-preferences:_")

    // jetpack lifecycle
    implementation(AndroidX.lifecycle.runtimeKtx)

    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.android)

    implementation(Google.android.playServices.base)

    implementation(AndroidX.lifecycle.liveDataKtx)

    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.compose.runtime.liveData)
    implementation(AndroidX.compose.ui.viewBinding)

    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)
}

hilt {
    enableAggregatingTask = true
}