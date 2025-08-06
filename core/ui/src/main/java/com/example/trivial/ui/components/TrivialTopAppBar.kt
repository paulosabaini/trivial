package com.example.trivial.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.R
import com.example.trivial.ui.theme.TrivialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrivialTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showBack: Boolean,
    onBack: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(R.drawable.chevron_backward),
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun TrivialTopAppBarPreview() {
    TrivialTheme {
        TrivialTopAppBar(title = "Trivial", showBack = true, onBack = {})
    }
}
