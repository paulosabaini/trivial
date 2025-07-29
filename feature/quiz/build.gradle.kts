plugins {
    alias(libs.plugins.trivial.android.library)
    alias(libs.plugins.trivial.android.feature)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.trivial.feature.quiz"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
}