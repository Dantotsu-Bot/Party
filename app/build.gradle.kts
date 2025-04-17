plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "io.sadwhy.party"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.sadwhy.party"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // BetterAndroid
    implementation("com.highcapable.betterandroid:ui-component:1.0.7")
    implementation("com.highcapable.betterandroid:ui-extension:1.0.6")
    implementation("com.highcapable.betterandroid:system-extension:1.0.2")
}
