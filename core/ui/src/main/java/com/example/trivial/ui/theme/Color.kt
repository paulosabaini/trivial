package com.example.trivial.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
object TrivialColors {
    // Monochromatic Base
    val black: Color = Color(0xFF000000)
    val white: Color = Color(0xFFFFFFFF)
    val gray100: Color = Color(0xFFF5F5F5)
    val gray200: Color = Color(0xFFEEEEEE)
    val gray300: Color = Color(0xFFE0E0E0)
    val gray400: Color = Color(0xFFBDBDBD)
    val gray500: Color = Color(0xFF9E9E9E)
    val gray600: Color = Color(0xFF757575)
    val gray700: Color = Color(0xFF616161)
    val gray800: Color = Color(0xFF424242)
    val gray900: Color = Color(0xFF212121)

    // Semantic Accents
    val success: Color = Color(0xFF43A047) // Slightly more vibrant but clean Green
    val onSuccess: Color = Color.White
    val error: Color = Color(0xFFE53935) // Slightly more vibrant but clean Red
    val onError: Color = Color.White

    // Mapping for Theme
    val primary: Color = black
    val onPrimary: Color = white
    val primaryDark: Color = white
    val onPrimaryDark: Color = black

    val secondary: Color = gray900
    val onSecondary: Color = white
    val secondaryDark: Color = gray100
    val onSecondaryDark: Color = black

    val background: Color = white
    val onBackground: Color = black
    val backgroundDark: Color = black
    val onBackgroundDark: Color = white

    val surface: Color = gray100
    val onSurface: Color = black
    val surfaceDark: Color = gray900
    val onSurfaceDark: Color = white
    
    val disabled: Color = gray300
    val onDisabled: Color = gray500
    val disabledDark: Color = gray800
    val onDisabledDark: Color = gray600
}

val LocalTrivialColors = staticCompositionLocalOf { TrivialColors }
