package com.example.trivial.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Placeholder for your custom display/headline font
// val GameFontFamily = FontFamily(Font(R.font.your_game_font_regular)) // Example

// Placeholder for your custom body/ui font
// val BodyFontFamily = FontFamily(Font(R.font.your_body_font_regular)) // Example

val TrivialTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = TrivialFontSize.DisplayLarge,
        lineHeight = TrivialLineHeight.LineHeightDisplayLarge,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointFive,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = TrivialFontSize.DisplayMedium,
        lineHeight = TrivialLineHeight.LineHeightDisplayMedium,
        letterSpacing = TrivialLetterSpacing.LetterSpacingZero
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = TrivialFontSize.DisplaySmall,
        lineHeight = TrivialLineHeight.LineHeightDisplaySmall,
        letterSpacing = TrivialLetterSpacing.LetterSpacingZero
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = TrivialFontSize.HeadlineLarge,
        lineHeight = TrivialLineHeight.LineHeightHeadlineLarge,
        letterSpacing = TrivialLetterSpacing.LetterSpacingZero
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = TrivialFontSize.HeadlineMedium,
        lineHeight = TrivialLineHeight.LineHeightHeadlineMedium,
        letterSpacing = TrivialLetterSpacing.LetterSpacingZero
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = TrivialFontSize.HeadlineSmall,
        lineHeight = TrivialLineHeight.LineHeightHeadlineSmall,
        letterSpacing = TrivialLetterSpacing.LetterSpacingZero
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = TrivialFontSize.TitleLarge,
        lineHeight = TrivialLineHeight.LineHeightTitleLarge,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointOne
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = TrivialFontSize.TitleMedium,
        lineHeight = TrivialLineHeight.LineHeightTitleMedium,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointOne
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = TrivialFontSize.TitleSmall,
        lineHeight = TrivialLineHeight.LineHeightTitleSmall,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointOne
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = TrivialFontSize.BodyLarge,
        lineHeight = TrivialLineHeight.LineHeightBodyLarge,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointFive
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = TrivialFontSize.BodyMedium,
        lineHeight = TrivialLineHeight.LineHeightBodyMedium,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointTwoFive
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = TrivialFontSize.BodySmall,
        lineHeight = TrivialLineHeight.LineHeightBodySmall,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointFour
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = TrivialFontSize.LabelLarge,
        lineHeight = TrivialLineHeight.LineHeightLabelLarge,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointTwo
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = TrivialFontSize.LabelMedium,
        lineHeight = TrivialLineHeight.LineHeightLabelMedium,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointFive
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = TrivialFontSize.LabelSmall,
        lineHeight = TrivialLineHeight.LineHeightLabelSmall,
        letterSpacing = TrivialLetterSpacing.LetterSpacingPointFive
    )
)
