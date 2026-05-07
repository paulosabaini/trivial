plugins {
    alias(libs.plugins.trivial.android.library)
    alias(libs.plugins.trivial.android.feature)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.trivial.feature.home"
}

dependencies {
//    implementation(project(":feature:quiz"))
}
