plugins {
    alias(libs.plugins.trivial.android.library)
    alias(libs.plugins.trivial.android.feature)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.koin.compiler)
}

android {
    namespace = "com.example.trivial.feature.quiz"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    implementation(libs.bundles.ktor)
}
