package com.example.trivial.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
object TrivialColors {
    // Primary Colors
    val primary: Color = Color(0xFF6200EE)
    val onPrimary: Color = Color.White
    val primaryDark: Color = Color(0xFF7B1FA2)
    val onPrimaryDark: Color = Color.White

    // Secondary Colors
    val secondary: Color = Color(0xFFF50057)
    val onSecondary: Color = Color.White
    val secondaryDark: Color = Color(0xFFF06292)
    val onSecondaryDark: Color = Color.Black

    // Tertiary Colors (Accent)
    val tertiary: Color = Color(0xFF03DAC5)
    val onTertiary: Color = Color.Black
    val tertiaryDark: Color = Color(0xFF4DD0E1)
    val onTertiaryDark: Color = Color.Black

    // Background and Surface Colors
    val background: Color = Color(0xFF392F6B)
    val onBackground: Color = Color.White
    val backgroundDark: Color = Color(0xFF121212)
    val onBackgroundDark: Color = Color.White

    val surface: Color = Color(0xFF4A3F80)
    val surfaceDark: Color = Color(0xFF1E1E1E)
    val onSurface: Color = Color.White
    val onSurfaceDark: Color = Color.White
    val onSurfaceVariant: Color = Color(0xFFB0A8D9)
    val onSurfaceVariantDark: Color = Color(0xFFBDBDBD)

    // Neutral & Utility Colors
    val neutralBlack: Color = Color(0xFF000000)
    val neutralBlackDark: Color = Color(0xFF000000)
    val neutralWhite: Color = Color(0xFFFFFFFF)
    val neutralWhiteDark: Color = Color(0xFFFFFFFF)
    val correctGreen: Color = Color(0xFF41CD72)
    val correctGreenDark: Color = Color(0xFF69F0AE)
    val incorrectRed: Color = Color(0xFFF53E57)
    val incorrectRedDark: Color = Color(0xFFFF5252)
    val disabledGrey: Color = Color(0xFF9E9E9E)
    val disabledGreyDark: Color = Color(0xFF616161)
    val onDisabled: Color = Color(0xFF616161)
    val onDisabledDark: Color = Color(0xFF424242)

    // Error Colors
    val errorDark: Color = Color(0xFFCF6679)
    val onErrorDark: Color = Color.Black

    // Extra Colors
    val pink: Color = Color(0XFFF661AC)
    val lightGrey: Color = Color(0xFFEFF3F6)
    val quizButtonContainerColor: Color = Color(0xFFEFF3F8)
    val quizButtonDisabledColor: Color = Color(0xFFFAFCFE)
    val quizButtonDisabledTextColor: Color = Color(0xFFC8CACB)
    val quizButtonSelectedColor: Color = Color(0xFF474D52)
}

val LocalTrivialColors = staticCompositionLocalOf { TrivialColors }
