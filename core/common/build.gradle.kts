import com.zucchini.buildsrc.Constants

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.zucchini.core.common"
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
    }
}

dependencies {
    implementation(project(":core:designsystem"))

    AndroidXDependencies.run {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(pagingRuntime)
    }

    KotlinDependencies.run {
        implementation(kotlin)
    }

    ThirdPartyDependencies.run {
        implementation(timber)
    }

    AndroidXDependencies.run {
        implementation(hilt)
    }

    TestDependencies.run {
        testImplementation(jUnit)
        androidTestImplementation(androidTest)
        androidTestImplementation(espresso)
    }

    KaptDependencies.run {
        kapt(hiltCompiler)
        kapt(hiltWorkManagerCompiler)
        kapt(hiltAndroidCompiler)
    }
}
