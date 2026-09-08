plugins {
    application
}

dependencies {
    implementation(project(":common"))
}

application {
    mainClass.set("com.sree.collections.CollectionsMain")
}
