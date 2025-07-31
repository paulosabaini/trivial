package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.theme.Size
import com.example.trivial.ui.theme.TrivialTheme

sealed interface QuizAnswerState {
    object Enabled : QuizAnswerState
    object Disabled : QuizAnswerState
    object Selected : QuizAnswerState
    object Correct : QuizAnswerState
    object Wrong : QuizAnswerState
}

@Composable
fun QuizAnswer(
    modifier: Modifier = Modifier,
    text: String,
    state: QuizAnswerState,
    highlight: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(Size.SizeExtraExtraLarge)
            .widthIn(min = Size.SizeExtraHuge),
        onClick = onClick,
        enabled = state != QuizAnswerState.Disabled,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = Size.SizeHairline, color = TrivialTheme.colors.neutralBlack),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = Size.SizeSmall),
        colors = getButtonColors(state)
    ) {
        Text(text = text.uppercase(), style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun getButtonColors(state: QuizAnswerState) = when (state) {
    QuizAnswerState.Enabled -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonContainerColor,
        contentColor = TrivialTheme.colors.neutralBlack,
    )

    QuizAnswerState.Correct -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.correctGreen,
        contentColor = TrivialTheme.colors.neutralBlack,
    )

    QuizAnswerState.Disabled -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonDisabledColor,
        contentColor = TrivialTheme.colors.quizButtonDisabledTextColor,
        disabledContainerColor = TrivialTheme.colors.quizButtonDisabledColor,
        disabledContentColor = TrivialTheme.colors.quizButtonDisabledTextColor
    )

    QuizAnswerState.Selected -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonSelectedColor,
        contentColor = TrivialTheme.colors.neutralWhite,
    )

    QuizAnswerState.Wrong -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.incorrectRed,
        contentColor = TrivialTheme.colors.neutralBlack,
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizAnswerPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuizAnswer(text = "Enabled", state = QuizAnswerState.Enabled) { }
            Spacer(Modifier.height(Size.SizeMedium))
            QuizAnswer(text = "Disabled", state = QuizAnswerState.Disabled) { }
            Spacer(Modifier.height(Size.SizeMedium))
            QuizAnswer(text = "Selected", state = QuizAnswerState.Selected) { }
            Spacer(Modifier.height(Size.SizeMedium))
            QuizAnswer(text = "Correct", state = QuizAnswerState.Correct) { }
            Spacer(Modifier.height(Size.SizeMedium))
            QuizAnswer(text = "Wrong", state = QuizAnswerState.Wrong) { }
        }
    }
}