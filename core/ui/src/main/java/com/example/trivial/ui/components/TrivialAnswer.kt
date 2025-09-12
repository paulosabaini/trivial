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
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme

sealed interface TrivialAnswerState {
    object Enabled : TrivialAnswerState
    object Disabled : TrivialAnswerState
    object Selected : TrivialAnswerState
    object Correct : TrivialAnswerState
    object Wrong : TrivialAnswerState
}

@Composable
fun TrivialAnswer(
    modifier: Modifier = Modifier,
    text: String,
    state: TrivialAnswerState,
    highlight: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(TrivialSize.SizeExtraExtraLarge)
            .widthIn(min = TrivialSize.SizeExtraHuge),
        onClick = onClick,
        enabled = state != TrivialAnswerState.Disabled,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = TrivialSize.SizeHairline, color = TrivialTheme.colors.neutralBlack),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = TrivialSize.SizeSmall),
        colors = getButtonColors(state)
    ) {
        Text(text = text.uppercase(), style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun getButtonColors(state: TrivialAnswerState) = when (state) {
    TrivialAnswerState.Enabled -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonContainerColor,
        contentColor = TrivialTheme.colors.neutralBlack,
    )

    TrivialAnswerState.Correct -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.correctGreen,
        contentColor = TrivialTheme.colors.neutralBlack,
    )

    TrivialAnswerState.Disabled -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonDisabledColor,
        contentColor = TrivialTheme.colors.quizButtonDisabledTextColor,
        disabledContainerColor = TrivialTheme.colors.quizButtonDisabledColor,
        disabledContentColor = TrivialTheme.colors.quizButtonDisabledTextColor
    )

    TrivialAnswerState.Selected -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.quizButtonSelectedColor,
        contentColor = TrivialTheme.colors.neutralWhite,
    )

    TrivialAnswerState.Wrong -> ButtonDefaults.buttonColors(
        containerColor = TrivialTheme.colors.incorrectRed,
        contentColor = TrivialTheme.colors.neutralBlack,
    )
}

@Preview(showBackground = true)
@Composable
private fun TrivialAnswerPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrivialAnswer(text = "Enabled", state = TrivialAnswerState.Enabled) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Disabled", state = TrivialAnswerState.Disabled) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Selected", state = TrivialAnswerState.Selected) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Correct", state = TrivialAnswerState.Correct) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Wrong", state = TrivialAnswerState.Wrong) { }
        }
    }
}