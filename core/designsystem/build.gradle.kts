import com.zucchini.buildsrc.Constants

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zucchini.core.designsystem"
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
}

dependencies {
}
