plugins {
    alias(libs.plugins.trivial.android.application)
    alias(libs.plugins.trivial.android.feature)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.trivial"

    defaultConfig {
        applicationId = "com.example.trivial"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":feature:quiz"))
}