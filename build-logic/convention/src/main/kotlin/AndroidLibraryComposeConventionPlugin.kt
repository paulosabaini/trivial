import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.kotlin.compose)
            }

            setupAndroidModule(isApplication = false)

            extensions.configure<CommonExtension> {
                buildFeatures.apply {
                    compose = true
                }
            }

            dependencies {
                implementation(platform(libs.androidx.compose.bom))
                debugImplementation(libs.androidx.ui.tooling)
            }
        }
    }
}