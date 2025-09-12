package com.example.trivial.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun TrivialCounter(
    modifier: Modifier = Modifier,
    count: Int = 0,
    min: Int = 0,
    max: Int = 50,
    onCountChanged: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(minWidth = TrivialSize.SizeHugeMedium)
            .background(TrivialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            onClick = {
                if (count > min) {
                    onCountChanged(count - 1)
                }
            },
            colors = IconButtonDefaults.iconButtonColors(contentColor = TrivialTheme.colors.onPrimary)
        ) { Text("-", style = MaterialTheme.typography.labelLarge) }
        Text(
            modifier = Modifier.padding(horizontal = TrivialSize.SizeSmall),
            text = count.toString(),
            style = MaterialTheme.typography.labelLarge,
            color = TrivialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = {
                if (count < max) {
                    onCountChanged(count + 1)
                }
            },
            colors = IconButtonDefaults.iconButtonColors(contentColor = TrivialTheme.colors.onPrimary)
        ) { Text("+", style = MaterialTheme.typography.labelLarge) }
    }
}

@Preview
@Composable
private fun TrivialCounterPreview() {
    TrivialTheme {
        var count by remember { mutableIntStateOf(0) }
        TrivialCounter(
            count = 0,
        ) { count = it }
    }
}