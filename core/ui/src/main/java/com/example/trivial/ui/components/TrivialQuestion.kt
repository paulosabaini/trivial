package com.example.trivial.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme
import kotlinx.coroutines.delay

@Composable
fun TrivialQuestion(
    modifier: Modifier = Modifier,
    question: String,
    answers: List<String>,
    correctAnswer: String,
    totalTimeSeconds: Int = 10,
    onCorrectAnswer: () -> Unit,
    onWrongAnswer: () -> Unit,
    onTimeUp: () -> Unit
) {
    var timeRemaining by remember { mutableIntStateOf(totalTimeSeconds) }
    var isRunning by remember { mutableStateOf(true) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (totalTimeSeconds > 0) timeRemaining.toFloat() / totalTimeSeconds.toFloat() else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ),
        label = "progress_animation"
    )

    LaunchedEffect(isRunning) {
        while (isRunning && timeRemaining > 0) {
            delay(1000)
            timeRemaining--
            if (timeRemaining == 0) {
                isRunning = false
                onTimeUp()
            }
        }
    }

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = TrivialSize.SizeSmall),
        colors = CardDefaults.cardColors(containerColor = TrivialTheme.colors.neutralWhite)
    ) {
        Column(modifier = Modifier.padding(TrivialSize.SizeMedium)) {
            Spacer(modifier = Modifier.height(TrivialSize.SizeMedium))
            TrivialProgressIndicator(modifier = Modifier.fillMaxWidth(), progress = animatedProgress, color = when {
                timeRemaining > totalTimeSeconds * 0.3 -> TrivialTheme.colors.pink
                else -> TrivialTheme.colors.incorrectRed
            })
            Spacer(modifier = Modifier.height(TrivialSize.SizeLarge))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = question,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = TrivialTheme.colors.neutralBlack
            )
            Spacer(modifier = Modifier.height(TrivialSize.SizeExtraLarge))
            answers.forEach { answer ->
                TrivialAnswer(
                    modifier = Modifier.fillMaxWidth(),
                    text = answer,
                    state = when {
                        selectedAnswer == null -> TrivialAnswerState.Enabled
                        answer == correctAnswer -> TrivialAnswerState.Correct
                        answer == selectedAnswer && answer != correctAnswer -> TrivialAnswerState.Wrong
                        !isRunning -> TrivialAnswerState.Disabled
                        else -> TrivialAnswerState.Disabled
                    },
                    onClick = {
                        if (selectedAnswer == null) {
                            selectedAnswer = answer
                            if (answer == correctAnswer) {
                                onCorrectAnswer()
                            } else {
                                onWrongAnswer()
                            }
                            isRunning = false
                        }
                    }
                )
                Spacer(modifier = Modifier.height(TrivialSize.SizeMedium))
            }
        }
    }
}

@Preview
@Composable
private fun TrivialQuestionPreview() {
    TrivialTheme {
        val question = "What is the capital of France?"
        val answers = listOf("Paris", "London", "Berlin", "Madrid")
        val correctAnswer = "Paris"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(TrivialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            TrivialQuestion(
                modifier = Modifier.padding(TrivialSize.SizeMedium),
                question = question,
                answers = answers,
                correctAnswer = correctAnswer,
                onCorrectAnswer = {},
                onWrongAnswer = {},
                onTimeUp = {}
            )
        }
    }
}
