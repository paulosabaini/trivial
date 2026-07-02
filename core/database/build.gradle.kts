plugins {
    alias(libs.plugins.trivial.kmp.library)
    alias(libs.plugins.room)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            implementation(libs.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    android {
        namespace = "com.example.trivial.database"
    }
}
