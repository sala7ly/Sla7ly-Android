// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
            google()
            mavenCentral()

        }

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath ("com.google.gms:google-services:4.4.0")

    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" // Depends on your kotlin version
    id("com.google.gms.google-services") version "4.3.15" apply false

}