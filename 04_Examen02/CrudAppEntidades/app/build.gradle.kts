plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.crudapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.crudapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Soporte para vector drawables en dispositivos antiguos
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Desactiva la minificación en modo release para depuración más sencilla
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false // Desactiva la minificación en debug
        }
    }

    compileOptions {
        // Compatibilidad con Java 8 para todas las clases usadas en el proyecto
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        // Configuración de Kotlin JVM
        jvmTarget = "1.8"
    }

    buildFeatures {
        // Habilita Jetpack Compose
        compose = true
    }

    composeOptions {
        // Versión del compilador de Compose
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            // Exclusión de recursos innecesarios para evitar conflictos
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Dependencias esenciales para el desarrollo con Android
    implementation(libs.androidx.core.ktx) // Extensiones de Kotlin para AndroidX
    implementation(libs.androidx.lifecycle.runtime.ktx) // Soporte para ViewModel y Lifecycle
    implementation(libs.androidx.activity.compose) // Soporte para actividades con Compose
    implementation(platform(libs.androidx.compose.bom)) // Bill of Materials para versiones consistentes

    // Jetpack Compose y Material Design 3
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Retrofit y Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Cliente HTTP Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose) // Conversor JSON para Retrofit
    implementation("com.google.android.material:material:1.9.0")

    // Testing
    testImplementation(libs.junit) // Librería para pruebas unitarias
    androidTestImplementation(libs.androidx.junit) // Pruebas de integración con AndroidX
    androidTestImplementation(libs.androidx.espresso.core) // Pruebas de UI con Espresso
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Dependencias de Compose para pruebas
    androidTestImplementation(libs.androidx.ui.test.junit4) // Soporte para pruebas con Compose

    // Debugging
    debugImplementation(libs.androidx.ui.tooling) // Herramientas de depuración para Compose
    debugImplementation(libs.androidx.ui.test.manifest) // Herramienta para depurar pruebas de manifiesto
}
