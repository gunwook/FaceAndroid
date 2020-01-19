// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra["koinVersion"] = "2.0.1"
    extra["retrofitVersion"] = "2.6.0"
    extra["okhttpVersion"] = "3.9.0"

    repositories {
        maven("https://jitpack.io")
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
