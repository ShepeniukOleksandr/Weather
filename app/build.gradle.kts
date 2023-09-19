plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.weather"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"


    }
    buildFeatures{
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.databinding:databinding-runtime:7.0.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("org.json:json:20210307")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}