package com.example.trivial.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.R
import com.example.trivial.ui.theme.TrivialSize
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
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = TrivialTheme.colors.disabledGrey,
            disabledContentColor = TrivialTheme.colors.onDisabled
        ),
        border = BorderStroke(width = TrivialSize.SizeHairline, color = TrivialTheme.colors.neutralBlack),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = TrivialSize.SizeSmall),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = null,
                tint = if (enabled) contentColor else TrivialTheme.colors.onDisabled
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrivialButtonPreview() {
    TrivialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrivialButton(text = "Enabled", onClick = {}, modifier = Modifier.fillMaxWidth())
            TrivialButton(text = "Disabled", enabled = false, onClick = {})
            TrivialButton(text = "Button", containerColor = TrivialTheme.colors.pink, onClick = {})
        }
    }
}