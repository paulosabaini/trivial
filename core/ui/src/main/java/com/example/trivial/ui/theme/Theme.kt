package com.example.trivial.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TrivialColors.primaryDark,
    onPrimary = TrivialColors.onPrimaryDark,
    secondary = TrivialColors.secondaryDark,
    onSecondary = TrivialColors.onSecondaryDark,
    tertiary = TrivialColors.tertiaryDark,
    onTertiary = TrivialColors.onTertiaryDark,
    background = TrivialColors.backgroundDark,
    onBackground = TrivialColors.onBackgroundDark,
    surface = TrivialColors.surfaceDark,
    onSurface = TrivialColors.onSurfaceDark,
    onSurfaceVariant = TrivialColors.onSurfaceVariantDark,
    error = TrivialColors.errorDark,
    onError = TrivialColors.onErrorDark,
)

private val LightColorScheme = lightColorScheme(
    primary = TrivialColors.primary,
    onPrimary = TrivialColors.onPrimary,
    secondary = TrivialColors.secondary,
    onSecondary = TrivialColors.onSecondary,
    tertiary = TrivialColors.tertiary,
    onTertiary = TrivialColors.onTertiary,
    background = TrivialColors.background,
    onBackground = TrivialColors.onBackground,
    surface = TrivialColors.surface,
    onSurface = TrivialColors.onSurface,
    onSurfaceVariant = TrivialColors.onSurfaceVariant,
    error = TrivialColors.incorrectRed,
    onError = TrivialColors.onPrimary,
)

@Composable
fun TrivialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val trivialColors = TrivialColors

    CompositionLocalProvider(LocalTrivialColors provides trivialColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TrivialTypography,
            shapes = TrivialShapes,
            content = content
        )
    }
}

object TrivialTheme {
    val colors: TrivialColors
        @Composable
        get() = LocalTrivialColors.current
}