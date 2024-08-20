import org.jetbrains.kotlin.incremental.createDirectory

plugins {
	`maven-publish`
	kotlin("jvm")
	kotlin("plugin.serialization")
	id("dev.architectury.loom")
	id("me.modmuss50.mod-publish-plugin")
	// UNCOMMENT TO USE MANIFOLD
	//id("systems.manifold.manifold-gradle-plugin")
}

// UNCOMMENT TO USE MANIFOLD
// The manifold Gradle plugin version. Update this if you update your IntelliJ Plugin!
// manifold { manifoldVersion = "2024.1.20" }

// Variables
class ModData {
	val id = property("mod.id").toString()
	val name = property("mod.name").toString()
	val version = property("mod.version").toString()
	val group = property("mod.group").toString()
	val description = property("mod.description").toString()
}

val mod = ModData()

val loader = loom.platform.get().name.lowercase()

val isFabric = loader == "fabric"
val isNeo = loader == "neoforge"
val isForge = loader == "forge"

val mcVersion = stonecutter.current.project.substringBeforeLast('-')
val mcDep = property("mod.mc_dep").toString()
val isSnapshot = hasProperty("env.snapshot")

version = "${mod.version}+$mcVersion"
group = mod.group
base {
	val capitalizedLoader = when (loader) {
        "neoforge" -> "NeoForge"
        else -> loader.replaceFirstChar { it.uppercase() }
    }

    archivesName.set("${mod.name}-$capitalizedLoader")
}

// Dependencies
repositories {
	fun strictMaven(url: String, vararg groups: String) = exclusiveContent {
		forRepository { maven(url) }
		filter { groups.forEach(::includeGroup) }
	}
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth")

	maven("https://jitpack.io")
	
	maven("https://maven.neoforged.net/releases/")
	maven("https://maven.minecraftforge.net")

	maven("https://maven.terraformersmc.com/releases/")
	maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
}

dependencies {
	fun modrinth(name: String, dep: Any?) = "maven.modrinth:$name:$dep"

	minecraft("com.mojang:minecraft:${mcVersion}")
	
	// UNCOMMENT TO USE MANIFOLD
	// Manifold Support
	// implementation(annotationProcessor("systems.manifold:manifold-preprocessor:${manifold.manifoldVersion.get()}")!!)

	@Suppress("UnstableApiUsage")
	mappings(loom.layered {
		officialMojangMappings()
	})

	if (isFabric) {
		modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fapi")}")
		modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")

		//if (mcVersion == "1.19.2")
			//
		//else
			//
	} else {
		if (isForge) {
			"forge"("net.minecraftforge:forge:${mcVersion}-${property("deps.fml")}")
		} else if (isNeo) "neoForge"("net.neoforged:neoforge:${property("deps.neoforge")}")
	}

	vineflowerDecompilerClasspath("org.vineflower:vineflower:1.10.1")
}

// Loom config
loom {
	// accessWidenerPath.set(rootProject.file("src/main/resources/necronomicon.accesswidener"))

	if (loader == "forge") forge {
		//convertAccessWideners.set(true)

		mixinConfigs(
			"${mod.id}-common.mixins.json"
		)
	} else if (loader == "neoforge") neoForge {

	}

	runConfigs["client"].apply {
		ideConfigGenerated(true)
		vmArgs("-Dmixin.debug.export=true")
		programArgs("--username=nthxny") // Mom look I'm in the codebase!
		runDir = "../../run"
	}

	decompilers {
		get("vineflower").apply {
			options.put("mark-corresponding-synthetics", "1")
		}
	}
}

// UNCOMMENT TO USE MANIFOLD

// // Tasks
// tasks.withType<JavaCompile>() {
// 	options.compilerArgs.add("-Xplugin:Manifold")
// 	// modify the JavaCompile task and inject our auto-generated Manifold symbols
// 	if(!this.name.startsWith("_")) { // check the name, so we don't inject into Forge internal compilation
// 		setupManifoldPreprocessors(options.compilerArgs, isFabric, projectDir, mcVersion, false)
// 	}
// }

// project.tasks.register("setupManifoldPreprocessors") {
// 	setupManifoldPreprocessors(ArrayList(), isFabric, projectDir, mcVersion, true)
// }

// tasks.setupChiseledBuild {
// 	finalizedBy("setupManifoldPreprocessors")
// }

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
	group = "build"
	from(tasks.remapJar.get().archiveFile)
	into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
	dependsOn("build")
}

if (stonecutter.current.isActive) {
	rootProject.tasks.register("buildActive") {
		group = "project"
		dependsOn(buildAndCollect)
	}

	rootProject.tasks.register("runActive") {
		group = "project"
		dependsOn(tasks.named("runClient"))
	}
}

// Resources
tasks.processResources {
	inputs.property("version", mod.version)
	inputs.property("mc", mcDep)
	inputs.property("name", mod.name)

	val map = mapOf(
		"version" to mod.version,
		"name" to mod.name,
		"description" to mod.description,
		"mod_id" to mod.id,
		"mc" to mcDep,
		"fml" to if (loader == "neoforge") "1" else "45",
		"mnd" to if (loader == "neoforge") "" else "mandatory = true"
	)

	filesMatching("fabric.mod.json") { expand(map) }
	filesMatching("META-INF/mods.toml") { expand(map) }
	filesMatching("META-INF/neoforge.mods.toml") { expand(map) }
}

//yamlang {
//	targetSourceSets.set(mutableListOf(sourceSets["main"]))
//	inputDir.set("assets/${mod.id}/lang")
//}

// Env configuration
stonecutter {
	val j21 = eval(mcVersion, ">=1.20.6")
	java {
		withSourcesJar()
		sourceCompatibility = if (j21) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
		targetCompatibility = if (j21) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
	}

	kotlin {
		jvmToolchain(if (j21) 21 else 17)
	}
}

// Publishing
publishMods {
	file = tasks.remapJar.get().archiveFile
	additionalFiles.from(tasks.remapSourcesJar.get().archiveFile)
	displayName =
		"${mod.name} ${loader.replaceFirstChar { it.uppercase() }} ${mod.version} for ${property("mod.mc_title")}"
	version = mod.version
	changelog = rootProject.file("CHANGELOG.md").readText()
	type = STABLE
	modLoaders.add(loader)

	val targets = property("mod.mc_targets").toString().split(' ')

	dryRun = providers.environmentVariable("MODRINTH_TOKEN")
		.getOrNull() == null || providers.environmentVariable("CURSEFORGE_TOKEN").getOrNull() == null

	modrinth {
		projectId = property("publish.modrinth").toString()
		accessToken = providers.environmentVariable("MODRINTH_TOKEN")
		targets.forEach(minecraftVersions::add)
		if (isFabric) {
			requires("fabric-api")
		}
	}

	curseforge {
		projectId = property("publish.curseforge").toString()
		accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
		targets.forEach(minecraftVersions::add)
		if (isFabric) {
			requires("fabric-api")
		}
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			groupId = "${property("mod.group")}.${mod.id}"
			artifactId = mod.version
			version = mcVersion

			from(components["java"])
		}
	}
}

fun setupManifoldPreprocessors(compilerArgs: MutableList<String>?, isFabric: Boolean, parent: File, mcString : String, clearMainProject : Boolean) {
	val mcVers = listOf("1.18.2", "1.19.2", "1.20.1", "1.21")
	val mcIndex = mcVers.indexOf(mcString);

	val argList = ArrayList<String>()
	for (i in mcVers.indices) {
		val mcStr = mcVers[i].replace(".", "_").substring(2)

		if (mcIndex < i) argList.add("BEFORE_$mcStr")
		if (mcIndex <= i) argList.add("UPTO_$mcStr")
		if (mcIndex == i) argList.add("CURRENT_$mcStr")
		if (mcIndex > i) argList.add("NEWER_THAN_$mcStr")
		if (mcIndex >= i) argList.add("AFTER_$mcStr")
	}

	argList.add(if (isFabric) "FABRIC" else if (isForge) "FORGE" else "NEOFORGE")

	val sb = StringBuilder().append("# DO NOT EDIT - GENERATED BY THE BUILD SCRIPT\n")
	for (arg in argList) {
		compilerArgs?.add("-A$arg")
		sb.append(arg).append("=\n")
	}

	File(parent, "build.properties").writeText(sb.toString())
	File(parent, "build/chiseledSrc").createDirectory()
	File(parent, "build/chiseledSrc/build.properties").writeText(sb.toString())

	// if the project we're currently processing annotations for happens to be the
	// main project, we need to also copy the build.properties to the root folder
	//val currentProject = mcString + "-" + (if (isFabric) "fabric" else "forge");
	//if (mainProject == currentProject)

	if (stonecutter.active.project == stonecutter.current.project)
		File(parent, "../../src/main/build.properties").writeText(sb.toString())

	if (clearMainProject)
		File(parent, "../../src/main/build.properties").delete()
}
