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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trivial.ui.components.TrivialButton
import com.example.trivial.ui.components.TrivialOutlinedButton
import com.example.trivial.ui.theme.TrivialSize

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
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "TRIVIAL",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 8.sp
            ),
            modifier = Modifier.padding(bottom = 64.dp)
        )

        TrivialButton(
            text = "Start Quiz",
            modifier = Modifier
                .fillMaxWidth()
                .height(TrivialSize.SizeExtraExtraLarge),
            onClick = onNavigateToQuiz
        )
        Spacer(modifier = Modifier.height(16.dp))
        TrivialOutlinedButton(
            text = "History",
            modifier = Modifier
                .fillMaxWidth()
                .height(TrivialSize.SizeExtraExtraLarge),
            onClick = onNavigateToHistory
        )
        Spacer(modifier = Modifier.height(16.dp))
        TrivialOutlinedButton(
            text = "Settings",
            modifier = Modifier
                .fillMaxWidth()
                .height(TrivialSize.SizeExtraExtraLarge),
            onClick = onNavigateToSettings
        )
    }
}
