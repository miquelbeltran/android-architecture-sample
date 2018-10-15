object Versions {
    val constraint = "1.1.3"
    val kotlin = "1.2.71"
    val navigation = "1.0.0-alpha06"
    val paging = "1.0.0"
    val supportLib = "28.0.0"
}

object Libs {
    val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraint}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "android.arch.navigation:navigation-ui:${Versions.navigation}"
    val paging = "android.arch.paging:runtime:${Versions.paging}"
    val supportAppcompatV7 = "com.android.support:appcompat-v7:${Versions.supportLib}"
    val koinAndroid = "org.koin:koin-android:1.0.1"
    val koinViewModel = "org.koin:koin-android-viewmodel:1.0.1"
}