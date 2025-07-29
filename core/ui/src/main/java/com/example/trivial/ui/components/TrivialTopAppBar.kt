package com.example.trivial.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.trivial.ui.R

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
                    Icon(painter = painterResource(R.drawable.chevron_backward), contentDescription = "Back")
                }
            }
        }
    )
}