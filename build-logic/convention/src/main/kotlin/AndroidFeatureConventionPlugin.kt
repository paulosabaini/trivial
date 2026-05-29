import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.kotlin.compose)
            }

            extensions.configure<CommonExtension> {
                buildFeatures.apply {
                    compose = true
                }
            }

            dependencies {
                implementation(project(":core:ui"))

                implementation(libs.androidx.navigation.compose)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.ui)
                implementation(libs.androidx.ui.graphics)
                implementation(libs.androidx.ui.tooling)
                implementation(libs.androidx.material3)
                implementation(libs.koin.androidx.compose)
                implementation(libs.koin.androidx.compose.navigation)
                implementation(libs.kotlinx.collections.immutable)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}