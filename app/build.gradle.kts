
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.gunwook.faceapp"
        minSdkVersion(15)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        unitTests.apply {
            this.isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    val koinVersion = rootProject.extra.get("koinVersion")
    val retrofitVersion = rootProject.extra.get("retrofitVersion")

    implementation("org.koin:koin-androidx-scope:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")
    implementation("org.koin:koin-androidx-ext:$koinVersion")
    testImplementation("org.koin:koin-test:$koinVersion")
    testImplementation("org.robolectric:robolectric:4.3.1")


    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.4")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")
    implementation("com.jakewharton.rxbinding3:rxbinding:3.0.0-alpha2")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("com.yanzhenjie:album:2.1.3")
    implementation("com.github.sembozdemir:PermissionsKt:1.0.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-inline:2.21.0")
    implementation("com.github.bumptech.glide:glide:4.9.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.9.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.google.code.gson:gson:2.8.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.1.0")

}

repositories {
    jcenter()
    mavenCentral()
    maven("http://repository.jetbrains.com/all")
}
