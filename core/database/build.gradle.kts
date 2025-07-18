plugins {
    alias(libs.plugins.trivial.android.library)
}

android {
    namespace = "com.example.trivial.database"
}

dependencies {
    implementation(libs.bundles.room)
}