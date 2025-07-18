plugins {
    alias(libs.plugins.trivial.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.trivial.network"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.bundles.ktor)
}