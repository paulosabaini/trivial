package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trivial.ui.theme.TrivialTheme

@Composable
fun TrivialButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val disabledContainerColor = if (darkTheme) TrivialTheme.colors.disabledDark else TrivialTheme.colors.disabled
    val disabledContentColor = if (darkTheme) TrivialTheme.colors.onDisabledDark else TrivialTheme.colors.onDisabled

    Button(
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = null,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TrivialOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrivialButtonPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrivialButton(text = "Primary Button", onClick = {}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            TrivialOutlinedButton(text = "Outlined Button", onClick = {}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            TrivialButton(text = "Disabled Button", enabled = false, onClick = {}, modifier = Modifier.fillMaxWidth())
        }
    }
}
