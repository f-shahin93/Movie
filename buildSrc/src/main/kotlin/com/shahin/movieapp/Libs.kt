package com.shahin.movieapp

object Libs {
    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
        const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    }

    object Jetpack {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val annotationsJet = "androidx.annotation:annotation:${Versions.annotation}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val pagination = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    }

    object Common {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshi}"
        const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stetho_OkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
        const val indicator = "com.tbuonomo:dotsindicator:${Versions.indicator}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.mockitoKotlin}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
        const val archTesting = "androidx.arch.core:core-testing:${Versions.archTest}"
        const val testCore = "androidx.test:core:${Versions.testCore}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val hamcrest = "org.hamcrest:hamcrest:${Versions.hamcrest}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    }

}