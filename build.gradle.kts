plugins {
    id("xyz.deftu.gradle.multiversion-root")
}

preprocess {
    val fabric11904 = createNode("1.19.4-fabric", 11903, "yarn")
    val forge11904 = createNode("1.19.4-forge", 11903, "srg")

    fabric11904.link(forge11904)
}

val releaseAllVersions by tasks.registering {
    listOf(
        "1.19.4-fabric",
        "1.19.4-forge"
    ).forEach { version ->
        dependsOn(":$version:releaseProject")
    }
}