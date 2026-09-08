plugins {
    base
}

group = "com.sree"
version = "1.0-SNAPSHOT"

val targetJavaVersion = providers
    .gradleProperty("javaVersion")
    .orElse("21")
    .get()
    .toInt()

require(targetJavaVersion in 8..26) {
    "javaVersion must be between 8 and 26. Current value: $targetJavaVersion"
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
            }
        }

        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
        }

        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }
}
