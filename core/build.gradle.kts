import com.zucchini.buildsrc.Constants
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.zucchini.core"
    compileSdk = Constants.compileSdk

    defaultConfig {
        minSdk = Constants.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.jvmVersion
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }
}

dependencies {
    // Kotlin
    implementation(KotlinDependencies.kotlin)

    // Lifecycle Ktx
    implementation(AndroidXDependencies.lifeCycleKtx)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltAndroidCompiler)

    // Test Dependency
    testImplementation(TestDependencies.jUnit)
    androidTestImplementation(TestDependencies.androidTest)
    androidTestImplementation(TestDependencies.espresso)
}
