plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.adamratzman.spotifyplayground"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("${System.getProperty("user.home")}/.android/debug.keystore")
            storePassword = property("keystorePassword") as String
            keyAlias = "androiddebugkey"
            keyPassword = property("debugKeyPassword") as String
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
    buildTypes {
        debug {
            isDebuggable = true
            buildConfigField("String", "SPOTIFY_CLIENT_ID", "\"0d7afea9e025418892dd31674b6170ee\"")
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_AUTH",
                "\"spotifyandroidplayground://spotify-auth\""
            )
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_PKCE",
                "\"spotifyandroidplayground://spotify-pkce\""
            )
        }
        /*release {
            minifyEnabled = false
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt'), 'proguard-rules.pro"
            buildConfigField("String", "SPOTIFY_CLIENT_ID", "\"0d7afea9e025418892dd31674b6170ee\"")
            buildConfigField("String", "SPOTIFY_REDIRECT_URI_AUTH", "\"spotifyandroidplayground://spotify-auth\"")
            buildConfigField("String", "SPOTIFY_REDIRECT_URI_PKCE", "\"spotifyandroidplayground://spotify-pkce\"")
        }*/
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // Images
    implementation("com.github.bumptech.glide:glide:4.13.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.1")
    implementation("com.github.skydoves:landscapist-glide:1.5.0" )

    // Spotify
    implementation("com.adamratzman:spotify-api-kotlin-android:3.8.6")

    implementation("androidx.compose.runtime:runtime:1.1.1")
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.foundation:foundation-layout:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("androidx.compose.foundation:foundation:1.1.1")
    implementation("androidx.compose.animation:animation:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.23.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.23.1")

    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    implementation("androidx.navigation:navigation-compose:2.4.1")

    implementation("androidx.window:window:1.0.0")


    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}