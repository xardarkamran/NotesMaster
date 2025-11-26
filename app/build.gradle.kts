plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.navigation.live.NoteMaster"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.navigation.live.NoteMaster"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Define product flavors
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationId = "com.navigation.live.NoteMaster"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "NoteMaster Dev")
            buildConfigField("Boolean", "IS_DEV", "true")
        }
        create("production") {
            dimension = "environment"
            applicationId = "com.navigation.live.NoteMaster"
            resValue("string", "app_name", "NoteMaster")
            buildConfigField("Boolean", "IS_DEV", "false")
        }
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
        buildConfig = true
    }
}

dependencies {
    // Module dependencies - app depends on presentation only
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose BOM - manages all Compose library versions
    implementation(platform(libs.androidx.compose.bom))

    // Navigation Compose - ADD THIS
    implementation(libs.androidx.navigation.compose)


    // Compose dependencies
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)

    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Hilt - Only for Application class (@HiltAndroidApp)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)
}