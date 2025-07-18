plugins {
    alias(libs.plugins.trivial.android.library.compose)
}

android {
    namespace = "com.example.trivial.ui"

}

dependencies {
    implementation(libs.androidx.material3)
}