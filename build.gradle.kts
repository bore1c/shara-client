import net.fabricmc.loom.task.RemapJarTask
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.withType

plugins {
    java
    id("fabric-loom") version "1.9.1"
}

group = "dev.shara"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

def minecraftVersion = "1.21.4"
def yarnMappings = "1.21.4+build.1"
def loaderVersion = "0.16.9"
def fabricApiVersion = "0.119.2+1.21.4"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

loom {
    // no extra access widener required for this scaffold
}
