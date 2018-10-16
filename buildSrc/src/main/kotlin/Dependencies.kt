@file:Suppress("unused")

object Versions {
    val appCompat = "1.0.0-alpha1"
    val constraint = "1.1.3"
    val koin = "1.0.1"
    val kotlin = "1.2.71"
    val navigation = "1.0.0-alpha06"
    val paging = "1.0.0"
    val retrofit = "2.4.0"
    val supportLib = "28.0.0"
    val testEspresso = "3.0.2"
    val testRunner = "1.0.2"
}

object Libs {
    val recycler = "com.android.support:recyclerview-v7:${Versions.supportLib}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraint}"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val junit = "junit:junit:4.12"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "android.arch.navigation:navigation-ui:${Versions.navigation}"
    val paging = "android.arch.paging:runtime:${Versions.paging}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val supportAppcompatV7 = "com.android.support:appcompat-v7:${Versions.supportLib}"
    val supportV4 = "com.android.support:support-v4:${Versions.supportLib}"
    val testEspresso = "com.android.support.test.espresso:espresso-core:${Versions.testEspresso}"
    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
}