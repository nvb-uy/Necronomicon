import com.modrinth.minotaur.dependencies.DependencyType
import com.modrinth.minotaur.dependencies.ModDependency
import org.gradle.configurationcache.extensions.capitalized
import xyz.deftu.gradle.tools.minecraft.CurseRelation
import xyz.deftu.gradle.tools.minecraft.CurseRelationType

plugins {
    java
    kotlin("jvm")
    id("xyz.deftu.gradle.multiversion")
    id("xyz.deftu.gradle.tools")
    id("xyz.deftu.gradle.tools.minecraft.loom")
    id("xyz.deftu.gradle.tools.shadow")
    id("xyz.deftu.gradle.tools.minecraft.releases")
}

val bundle by configurations.creating {
    // Fabric imposes a hard limit of 64 on mod IDs
    // the autogenned mod IDs are far longer than that
    // thanks, netty!
    if (false /* mcData.isFabric */) {
        configurations.getByName("include").extendsFrom(this)
    } else configurations.getByName("shade").extendsFrom(this)
}

toolkitLoomHelper {
    if (mcData.isForge) {
        useForgeMixin("necronomicon.forge.mixins.json", true)
    }
}

java {
    withSourcesJar()
}

toolkitReleases {
    gameVersions.set(when (mcData.version) {
        12001 -> listOf("1.20", "1.20.1", "1.20.2")
        11904 -> listOf("1.19", "1.19.1", "1.19.2", "1.19.3", "1.19.4")
        else -> listOf()
    })
    releaseName.set("[${when (mcData.version) {
        12001 -> "1.20-"
        11904 -> "1.19-"
        else -> mcData.versionStr
    }}] [${mcData.loader.name.capitalized()}] ${modData.version}")
    if (mcData.isFabric) {
        loaders.set(listOf("fabric", "quilt"))
    }
}

repositories {
    maven("https://maven.terraformersmc.com/")
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    implementation(kotlin("stdlib"))

    if (mcData.isFabric) {
        modImplementation(
            "net.fabricmc.fabric-api:fabric-api:${
                when (mcData.version) {
                    11904 -> "0.78.0+1.19.4"
                    11802 -> "0.76.0+1.18.2"
                    else -> throw IllegalStateException("Invalid MC version: ${mcData.version}")
                }
            }"
        )

        modImplementation("net.fabricmc:fabric-language-kotlin:1.8.6+kotlin.1.7.21")
    } else if (mcData.isForge) {
        implementation("thedarkcolour:kotlinforforge:3.8.0")
    }
}