package com.example.trivial.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeDisplayLarge,
        lineHeight = LineHeightDisplayLarge,
        letterSpacing = LetterSpacingDisplayLarge
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSizeHeadlineLarge,
        lineHeight = LineHeightHeadlineLarge,
        letterSpacing = LetterSpacingDefault
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTitleLarge,
        lineHeight = LineHeightTitleLarge,
        letterSpacing = LetterSpacingDefault
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeBodyLarge,
        lineHeight = LineHeightBodyLarge,
        letterSpacing = LetterSpacingBodyLarge
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeLabelSmall,
        lineHeight = LineHeightLabelSmall,
        letterSpacing = LetterSpacingLabelSmall
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeLabelMedium,
        lineHeight = LineHeightLabelMedium,
        letterSpacing = LetterSpacingLabelSmall
    ),
)
