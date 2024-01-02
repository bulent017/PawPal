// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        val nav_version = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}
