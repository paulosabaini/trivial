package com.example.trivial.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.theme.Size
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun OptionsSelector(
    modifier: Modifier = Modifier,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    LazyRow {
        items(options) {
            OptionButton(
                modifier = modifier,
                text = it,
                isSelected = it == selectedOption,
                onClick = { onOptionSelected(it) }
            )
        }
    }
}

@Composable
private fun OptionButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (isSelected) TrivialTheme.colors.primary else TrivialTheme.colors.neutralWhite,
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(Size.SizeMedium),
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) TrivialTheme.colors.onPrimary else TrivialTheme.colors.neutralBlack,
        )
    }
}

@Preview
@Composable
fun OptionButtonPreview() {
    TrivialTheme {
        Row {
            OptionButton(
                text = "One", isSelected = true
            ) {}
            OptionButton(
                text = "Two", isSelected = false
            ) {}
        }
    }
}

@Preview
@Composable
fun OptionsSelectorPreview() {
    TrivialTheme {
        var selectedOption by remember { mutableStateOf("Option 1") }
        OptionsSelector(
            selectedOption = selectedOption,
            options = listOf(
                "Option 1",
                "Option 2",
                "Option 3"
            ),
            onOptionSelected = { selectedOption = it }
        )
    }
}