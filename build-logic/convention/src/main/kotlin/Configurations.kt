import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureKmpLibrary(iosFrameworkBaseName: String? = null) {
    with(pluginManager) {
        alias(libs.plugins.kotlin.multiplatform)
        alias(libs.plugins.android.kotlin.multiplatform.library)
        alias(libs.plugins.ksp)
    }

    extensions.configure<KotlinMultiplatformExtension> {
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xannotation-default-target=param-property"))
        }
        configure<KotlinMultiplatformAndroidLibraryTarget> {
            compileSdk {
                version = release(libs.versions.android.compileSdk.get().toInt())
            }
            minSdk = libs.versions.android.minSdk.get().toInt()
        }

        val iosTargets = listOf(
            iosArm64(),
            iosSimulatorArm64()
        )
        if (iosFrameworkBaseName != null) {
            iosTargets.forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = iosFrameworkBaseName
                    isStatic = true
                }
            }
        }

        jvm()
    }
}

fun Project.configureCompose() {
    with(pluginManager) {
        alias(libs.plugins.jetbrains.compose)
    }


}