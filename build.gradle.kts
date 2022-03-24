// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath(Google.dagger.hilt.android.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
    }

    // todo: with below configureEach, all of the kotlinOptions {} imho is safe to remove,
    //  but it might bite, so we still didn't remove it :)))
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.apply {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs += listOf(
                "-Xjvm-default=all-compatibility",
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
            )
        }
    }
}

val clean by tasks.registering(Delete::class) {
    delete = setOf(
        rootProject.buildDir,
        "buildSrc/build",
        "buildSrc/lint",
        "buildSrc/.gradle",
        "$rootProject/lint",
        ".gradle",
        "lint",
        ".idea/libraries",
        ".idea/modules",
        ".idea/modules.xml",
        ".idea/caches",
    )
}