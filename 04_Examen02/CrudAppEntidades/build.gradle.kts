// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    dependencies {
        // Plugin de Gradle para construir proyectos de Android
        classpath("com.android.tools.build:gradle:8.2.1")
        // Plugin de Kotlin para proyectos
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}

// Tarea para limpiar el directorio de construcción
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
