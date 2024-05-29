import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.zucchini.buildsrc.Constants

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Versions.kotlinVersion

}

android {
    namespace = "com.zucchini.core.network"
    compileSdk = Constants.compileSdk

    defaultConfig {
        minSdk = Constants.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                gradleLocalProperties(rootDir).getProperty("base.url") ?: "\"\"",
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                gradleLocalProperties(rootDir).getProperty("base.url") ?: "\"\"",
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":core:common"))

    KotlinDependencies.run {
        implementation(kotlin)
        implementation(coroutines)
        implementation(jsonSerialization)
    }

    ThirdPartyDependencies.run {
        implementation(platform(okHttpBom))
        implementation(okHttp)
        implementation(okHttpLoggingInterceptor)
        implementation(retrofit)
        implementation(retrofitJsonConverter)
        implementation(timber)
        implementation(ossLicense)
        implementation(kakaoLogin)
        implementation(processPhoenix)
    }

    AndroidXDependencies.run {
        implementation(hilt)
        implementation(workManager)
        implementation(hiltWorkManager)
    }

    KaptDependencies.run {
        kapt(hiltCompiler)
        kapt(hiltWorkManagerCompiler)
        kapt(hiltAndroidCompiler)

    }
}
