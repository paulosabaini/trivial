plugins {
    alias(libs.plugins.trivial.kmp.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    android {
        namespace = "com.example.trivial.network"
    }
}
