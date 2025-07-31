package com.example.trivial.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trivial.ui.theme.Size
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier.height(Size.SizeSmall),
        color = TrivialTheme.colors.pink,
        trackColor = TrivialTheme.colors.lightGrey,
        gapSize = Size.SizeNone,
        drawStopIndicator = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ProgressBarPreview() {
    ProgressIndicator(
        modifier = Modifier,
        progress = 0.5f
    )
}