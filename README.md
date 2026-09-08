# Java Practice Studio

A Gradle multi-module project with one shared library and several independently
runnable practice applications. All subprojects live under the `modules/` directory
to keep the repository root tidy.

## Modules

| Module        | Type         | Depends on | Purpose                              |
|---------------|--------------|------------|--------------------------------------|
| `common`      | Java library | —          | Utilities shared by practice modules |
| `basics`      | Application  | `common`   | Language and control-flow exercises  |
| `collections` | Application  | `common`   | Java collections exercises           |
| `concurrency` | Application  | `common`   | Threads and executor exercises       |

Each application owns its entry point, so it can be run without running the other
application modules:

```shell
./gradlew :basics:run
./gradlew :collections:run
./gradlew :concurrency:run
```

Gradle automatically builds `common` when an application needs it. You can also
build or test a single module, or the entire project:

```shell
./gradlew :common:test
./gradlew :collections:build
./gradlew build
```

The Java toolchain version is controlled centrally in `gradle.properties`.

## Add another application module

Add the module name to the list in `settings.gradle.kts` (the existing loop maps it
to `modules/<name>`):

```kotlin
listOf("common", "basics", "collections", "concurrency", "streams").forEach { moduleName ->
    include(moduleName)
    project(":$moduleName").projectDir = file("modules/$moduleName")
}
```

Then create `modules/streams/build.gradle.kts`:

```kotlin
plugins {
    application
}

dependencies {
    implementation(project(":common"))
}

application {
    mainClass.set("com.sree.streams.StreamsMain")
}
```

The new module is independently runnable with `./gradlew :streams:run`.

## Add dependencies between modules

In the consuming module, use `implementation` for an internal dependency:

```kotlin
dependencies {
    implementation(project(":common"))
}
```

For a library whose public API exposes types from another module, use `api` instead:

```kotlin
dependencies {
    api(project(":common"))
}
```
