package com.example.trivial.feature.quiz.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun QuizRoute(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = koinViewModel(),
    onStartQuizClick: () -> Unit
) {
    QuizSetupScreen(
        modifier = modifier,
        onStartQuizClick = onStartQuizClick,
    )
}

@Composable
internal fun QuizSetupScreen(
    modifier: Modifier = Modifier,
    onStartQuizClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {

    }
}
