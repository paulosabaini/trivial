import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs
    get(): LibrariesForLibs = extensionOf(this, "libs") as LibrariesForLibs

fun PluginManager.alias(notation: Provider<PluginDependency>) {
    apply(notation.get().pluginId)
}

fun PluginManager.alias(notation: ProviderConvertible<PluginDependency>) {
    apply(notation.asProvider().get().pluginId)
}

fun DependencyHandler.implementation(provider: Provider<MinimalExternalModuleDependency>) {
    add(
        "implementation",
        provider.get().group + ":" + provider.get().name + ":" + provider.get().version
    )
}

fun DependencyHandler.implementation(project: Any) {
    add("implementation", project)
}

fun DependencyHandler.debugImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add(
        "debugImplementation",
        provider.get().group + ":" + provider.get().name + ":" + provider.get().version
    )
}

fun DependencyHandler.ksp(provider: Provider<MinimalExternalModuleDependency>) {
    add("ksp", provider.get().group + ":" + provider.get().name + ":" + provider.get().version)
}

fun Project.setupAndroidModule(isApplication: Boolean) {
    with(pluginManager) {
        if (isApplication) {
            alias(libs.plugins.android.application)
        } else {
            alias(libs.plugins.android.library)
        }
        alias(libs.plugins.ksp)
    }

    if (isApplication) {
        extensions.configure<ApplicationExtension> {
            configureAndroidCommon(this)
        }
    } else {
        extensions.configure<LibraryExtension> {
            configureAndroidCommon(this)
        }
    }
}

private fun Project.configureAndroidCommon(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = 37

        defaultConfig.apply {
            minSdk = 26
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        if (this is ApplicationExtension) {
            defaultConfig.apply {
                targetSdk = 37
                versionCode = 1
                versionName = "1.0"
            }
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        buildTypes.apply {
            maybeCreate("debug")
            maybeCreate("release")
            getByName("release").apply {
                isMinifyEnabled = true
                proguardFiles(
                    "proguard-android-optimize.txt",
                    "proguard-rules.pro"
                )
            }
        }

        packaging.resources.apply {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

        configureKotlin()
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
}

fun Project.setupBaseDependencies() {
    dependencies {
        implementation(libs.androidx.core.ktx)

        // Coroutines
        implementation(libs.kotlin.coroutines.core)
        implementation(libs.kotlin.coroutines.android)

        // Koin
        implementation(libs.koin.android)
        implementation(libs.koin.annotations)
        ksp(libs.koin.ksp.compiler)
        implementation(libs.koin.core.viewmodel)

        // Timber
        implementation(libs.timber)
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    configureKotlin()
}
