package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
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
    Surface(
        modifier = modifier.defaultMinSize(minWidth = TrivialSize.SizeHugeMedium),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, TrivialTheme.colors.gray300),
        color = TrivialTheme.colors.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = {
                    if (count > min) {
                        onCountChanged(count - 1)
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(contentColor = TrivialTheme.colors.onBackground)
            ) { Text("-", style = MaterialTheme.typography.titleMedium) }
            Text(
                modifier = Modifier.padding(horizontal = TrivialSize.SizeSmall),
                text = count.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = TrivialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    if (count < max) {
                        onCountChanged(count + 1)
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(contentColor = TrivialTheme.colors.onBackground)
            ) { Text("+", style = MaterialTheme.typography.titleMedium) }
        }
    }
}

@Preview
@Composable
private fun TrivialCounterPreview() {
    TrivialTheme {
        var count by remember { mutableIntStateOf(10) }
        TrivialCounter(
            count = count,
            onCountChanged = { count = it }
        )
    }
}
