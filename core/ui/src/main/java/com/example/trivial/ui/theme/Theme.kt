package com.example.trivial.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue30,
    onPrimary = Neutral100,
    primaryContainer = Blue30,
    onPrimaryContainer = Neutral90,
    secondary = Orange30,
    onSecondary = Neutral100,
    secondaryContainer = Orange30,
    onSecondaryContainer = Neutral90,
    tertiary = Teal30,
    onTertiary = Neutral100,
    tertiaryContainer = Teal30,
    onTertiaryContainer = Neutral90,
    error = Red40,
    onError = Neutral100,
    errorContainer = Red80,
    onErrorContainer = DarkRed20,
    background = Neutral6,
    onBackground = Neutral90,
    surface = Neutral10,
    onSurface = Neutral90,
    surfaceVariant = Neutral15,
    onSurfaceVariant = Neutral70,
    outline = Neutral30
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = Neutral100,
    primaryContainer = Blue80,
    onPrimaryContainer = Blue30,
    secondary = Orange40,
    onSecondary = Neutral0,
    secondaryContainer = Orange80,
    onSecondaryContainer = Orange30,
    tertiary = Teal40,
    onTertiary = Neutral0,
    tertiaryContainer = Teal80,
    onTertiaryContainer = Teal30,
    error = Red40,
    onError = Neutral100,
    errorContainer = Red80,
    onErrorContainer = DarkRed20,
    background = Neutral99,
    onBackground = Neutral20,
    surface = Neutral90,
    onSurface = Neutral20,
    surfaceVariant = Neutral95,
    onSurfaceVariant = Neutral20,
    outline = Neutral70
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}