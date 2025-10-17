plugins {
    alias(libs.plugins.trivial.android.library)
    alias(libs.plugins.room)
}

android {
    namespace = "com.example.trivial.database"
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)
}