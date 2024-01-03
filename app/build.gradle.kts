plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.bulentoral.pawpal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bulentoral.pawpal"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.firebase:firebase-messaging:23.4.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val navVersion = "2.7.6"

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.android.material:material:1.11.0")

    //Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    //Firebase analytics
    implementation("com.google.firebase:firebase-analytics")

    //Firebase auth
    implementation("com.google.firebase:firebase-auth")

    //RxJava
    implementation ("io.reactivex.rxjava2:rxjava:2.2.5")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.0")

    //Firestore and http
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.10.0")
    implementation ("com.google.firebase:firebase-storage-ktx:20.3.0")
    implementation ("com.google.firebase:firebase-messaging-ktx:23.4.0")
    implementation("com.google.firebase:firebase-messaging:23.4.0.")

    //retrofit ve json
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-jackson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")


    //rxjava3
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5") // En son sürümü belirtin
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2") // En son sürümü belirtin

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //some image lib, not ready to use
    //implementation ("com.github.dhaval2404:imagepicker:2.1")
    //image picker
    implementation ("com.github.dhaval2404:imagepicker:2.1")

}