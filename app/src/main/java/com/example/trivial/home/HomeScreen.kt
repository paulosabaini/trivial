package com.example.trivial.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trivial.ui.components.ButtonPrimary

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    onNavigateToQuiz: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    HomeScreen(
        modifier = modifier,
        onNavigateToQuiz = onNavigateToQuiz,
        onNavigateToHistory = onNavigateToHistory,
        onNavigateToSettings = onNavigateToSettings
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToQuiz: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ButtonPrimary(text = "Start Quiz", modifier = Modifier.fillMaxWidth(), onClick = onNavigateToQuiz)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonPrimary(text = "History", modifier = Modifier.fillMaxWidth(), onClick = onNavigateToHistory)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonPrimary(text = "Settings", modifier = Modifier.fillMaxWidth(), onClick = onNavigateToSettings)
    }
}
