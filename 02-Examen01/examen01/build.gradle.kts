plugins {
    kotlin("jvm") version "2.0.0"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

javafx {
    version = "17"  // Ajusta la versión si es necesario
    modules = listOf("javafx.controls", "javafx.fxml")  // Agrega otros módulos si es necesario
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.8.9") // O la versión más reciente
    implementation("org.openjfx:javafx-controls:17")
    implementation("org.openjfx:javafx-fxml:17")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}