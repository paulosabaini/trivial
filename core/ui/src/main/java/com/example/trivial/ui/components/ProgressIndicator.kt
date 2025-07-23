package com.example.trivial.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier.height(8.dp),
        color = color,
        gapSize = 0.dp,
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