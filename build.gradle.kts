buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPathPlugins.gradle)
        classpath(ClassPathPlugins.kotlinGradle)
        classpath(ClassPathPlugins.hilt)
        classpath(ClassPathPlugins.oss)
        classpath(ClassPathPlugins.compose)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
