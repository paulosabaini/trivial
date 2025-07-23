package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trivial.ui.theme.Neutral0
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        border = BorderStroke(width = 1.dp, color = Neutral0),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonPrimaryPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonPrimary(text = "Enabled", onClick = {})
            ButtonPrimary(text = "Disabled", enabled = false, onClick = {})
            ButtonPrimary(
                text = "Selected",
                buttonColor = MaterialTheme.colorScheme.secondary,
                onClick = {},
            )
            ButtonPrimary(text = "Correct", onClick = {})
            ButtonPrimary(text = "Wrong", onClick = {})
            ButtonPrimary(text = "Button", onClick = {})
        }
    }
}