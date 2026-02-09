import java.util.Locale

plugins {
    id("java")
    application
}

group = "com.AngelaAmor.tema4gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")

}
application {
    mainClass.set("com.AngelaAmor.tema4gradle.Main")
}

tasks.test {
    useJUnitPlatform()
}
tasks.register<JavaExec>("runMain") {
    group = "application"
    description = "Ejecuta Main.java (ejercicios 6-7)"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.AngelaAmor.tema4gradle.Main")
}

tasks.register<JavaExec>("runDebateIA") {
    group = "application"
    description = "Ejecuta DebateIA.java (ejercicio 8)"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.AngelaAmor.tema4gradle.DebateIA")
}
// Tarea 1: ¿Está Ollama vivo?
tasks.register<Exec>("ollamaVersion") {
    group = "ollama"
    description = "Muestra la versión de Ollama instalada"

    // Detecta sistema operativo
    if (System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")) {
        commandLine("cmd", "/c", "ollama", "--version")
    } else {
        commandLine("ollama", "--version")
    }
}
// Tarea 2: ¿Hay modelos cargados?
tasks.register<Exec>("ollamaPs") {
    group = "ollama"
    description = "Muestra los modelos de Ollama en ejecución"

    if (System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")) {
        commandLine("cmd", "/c", "ollama", "ps")
    } else {
        commandLine("ollama", "ps")
    }
}
// Tarea 3: Información completa
tasks.register("llmInfo") {
    group = "ollama"
    description = "Muestra información completa del sistema LLM"

    dependsOn("ollamaVersion", "ollamaPs")

    doLast {
        println("*********************************************")
        println("Demo finalizada - Sistema LLM listo")
        println("*********************************************")
    }
}
