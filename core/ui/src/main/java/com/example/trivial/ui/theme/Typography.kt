package com.example.trivial.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Placeholder for your custom display/headline font
// val GameFontFamily = FontFamily(Font(R.font.your_game_font_regular)) // Example

// Placeholder for your custom body/ui font
// val BodyFontFamily = FontFamily(Font(R.font.your_body_font_regular)) // Example

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.DisplayLarge,
        lineHeight = LineHeight.LineHeightDisplayLarge,
        letterSpacing = LetterSpacing.LetterSpacingPointFive,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.DisplayMedium,
        lineHeight = LineHeight.LineHeightDisplayMedium,
        letterSpacing = LetterSpacing.LetterSpacingZero
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.DisplaySmall,
        lineHeight = LineHeight.LineHeightDisplaySmall,
        letterSpacing = LetterSpacing.LetterSpacingZero
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.HeadlineLarge,
        lineHeight = LineHeight.LineHeightHeadlineLarge,
        letterSpacing = LetterSpacing.LetterSpacingZero
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.HeadlineMedium,
        lineHeight = LineHeight.LineHeightHeadlineMedium,
        letterSpacing = LetterSpacing.LetterSpacingZero
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.HeadlineSmall,
        lineHeight = LineHeight.LineHeightHeadlineSmall,
        letterSpacing = LetterSpacing.LetterSpacingZero
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.TitleLarge,
        lineHeight = LineHeight.LineHeightTitleLarge,
        letterSpacing = LetterSpacing.LetterSpacingPointOne
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.TitleMedium,
        lineHeight = LineHeight.LineHeightTitleMedium,
        letterSpacing = LetterSpacing.LetterSpacingPointOne
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = FontSize.TitleSmall,
        lineHeight = LineHeight.LineHeightTitleSmall,
        letterSpacing = LetterSpacing.LetterSpacingPointOne
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.BodyLarge,
        lineHeight = LineHeight.LineHeightBodyLarge,
        letterSpacing = LetterSpacing.LetterSpacingPointFive
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.BodyMedium,
        lineHeight = LineHeight.LineHeightBodyMedium,
        letterSpacing = LetterSpacing.LetterSpacingPointTwoFive
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.BodySmall,
        lineHeight = LineHeight.LineHeightBodySmall,
        letterSpacing = LetterSpacing.LetterSpacingPointFour
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.LabelLarge,
        lineHeight = LineHeight.LineHeightLabelLarge,
        letterSpacing = LetterSpacing.LetterSpacingPointTwo
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.LabelMedium,
        lineHeight = LineHeight.LineHeightLabelMedium,
        letterSpacing = LetterSpacing.LetterSpacingPointFive
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = FontSize.LabelSmall,
        lineHeight = LineHeight.LineHeightLabelSmall,
        letterSpacing = LetterSpacing.LetterSpacingPointFive
    )
)
