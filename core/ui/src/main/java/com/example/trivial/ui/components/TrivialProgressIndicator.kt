package com.example.trivial.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun TrivialProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    color: Color = TrivialTheme.colors.pink,
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier.height(TrivialSize.SizeSmall),
        color = color,
        trackColor = TrivialTheme.colors.lightGrey,
        gapSize = TrivialSize.SizeNone,
        drawStopIndicator = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun TrivialProgressBarPreview() {
    TrivialProgressIndicator(
        modifier = Modifier,
        progress = 0.5f
    )
}