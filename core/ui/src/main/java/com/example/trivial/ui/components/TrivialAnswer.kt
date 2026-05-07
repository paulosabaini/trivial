package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onClick: () -> Unit
) {
    val borderColor = getBorderColor(state)
    val containerColor = getContainerColor(state)
    val contentColor = getContentColor(state)

    OutlinedButton(
        modifier = modifier
            .height(TrivialSize.SizeExtraExtraLarge)
            .widthIn(min = TrivialSize.SizeExtraHuge),
        onClick = onClick,
        enabled = state != TrivialAnswerState.Disabled,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, borderColor),
        elevation = null,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun getBorderColor(state: TrivialAnswerState): Color = when (state) {
    TrivialAnswerState.Enabled -> TrivialTheme.colors.gray300
    TrivialAnswerState.Correct -> TrivialTheme.colors.success
    TrivialAnswerState.Disabled -> TrivialTheme.colors.gray200
    TrivialAnswerState.Selected -> TrivialTheme.colors.black
    TrivialAnswerState.Wrong -> TrivialTheme.colors.error
}

@Composable
private fun getContainerColor(state: TrivialAnswerState): Color = when (state) {
    TrivialAnswerState.Selected -> TrivialTheme.colors.black
    TrivialAnswerState.Correct -> TrivialTheme.colors.success.copy(alpha = 0.1f)
    TrivialAnswerState.Wrong -> TrivialTheme.colors.error.copy(alpha = 0.1f)
    else -> Color.Transparent
}

@Composable
private fun getContentColor(state: TrivialAnswerState): Color = when (state) {
    TrivialAnswerState.Selected -> TrivialTheme.colors.white
    TrivialAnswerState.Correct -> TrivialTheme.colors.success
    TrivialAnswerState.Wrong -> TrivialTheme.colors.error
    TrivialAnswerState.Disabled -> TrivialTheme.colors.onDisabled
    else -> TrivialTheme.colors.onBackground
}

@Preview(showBackground = true)
@Composable
private fun TrivialAnswerPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrivialAnswer(text = "Enabled Answer", state = TrivialAnswerState.Enabled) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Selected Answer", state = TrivialAnswerState.Selected) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Correct Answer", state = TrivialAnswerState.Correct) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Wrong Answer", state = TrivialAnswerState.Wrong) { }
            Spacer(Modifier.height(TrivialSize.SizeMedium))
            TrivialAnswer(text = "Disabled Answer", state = TrivialAnswerState.Disabled) { }
        }
    }
}
