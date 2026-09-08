plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "java-practice-studio"

listOf("common", "basics", "collections", "concurrency").forEach { moduleName ->
    include(moduleName)
    project(":$moduleName").projectDir = file("modules/$moduleName")
}
