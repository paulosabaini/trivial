package com.example.trivial.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun TrivialOptionsSelector(
    modifier: Modifier = Modifier,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = TrivialTheme.colors.primary,
                    activeContentColor = TrivialTheme.colors.onPrimary,
                    inactiveContainerColor = TrivialTheme.colors.neutralWhite,
                    inactiveContentColor = TrivialTheme.colors.neutralBlack,
                )
            ) {
                Text(
                    modifier = Modifier.padding(TrivialSize.SizeMedium),
                    text = option,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TrivialOptionsSelectorPreview() {
    TrivialTheme {
        var selectedOption by remember { mutableStateOf("Option 1") }
        TrivialOptionsSelector(
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