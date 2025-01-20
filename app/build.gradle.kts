plugins {
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android.main)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.nutritionscanning"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nutritionscanning"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Data Store
    implementation(libs.androidx.datastore.preferences)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Material
    implementation(libs.androidx.material)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // CameraX
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Lottie
    implementation(libs.lottie)
}