import com.zucchini.buildsrc.Constants

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.zucchini.feature.designsystem"
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
        buildConfig = true
        dataBinding = true
        viewBinding = true
        compose = true
    }
}

dependencies {
    KotlinDependencies.run {
        implementation(kotlin)
    }
}
