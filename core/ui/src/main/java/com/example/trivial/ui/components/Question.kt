package com.example.trivial.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Question(
    modifier: Modifier = Modifier,
    question: String,
    answers: List<String>,
    correctAnswer: String,
    onAnswerSelected: (String) -> Unit
) {
    var progress by remember { mutableFloatStateOf(1f) }
    val animatedProgress by
    animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProgressIndicator(modifier = Modifier.fillMaxWidth(), progress = animatedProgress)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = question,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            answers.forEach { answer ->
                ButtonPrimary(
                    modifier = Modifier.fillMaxWidth(),
                    text = answer,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun QuestionPreview() {
    val question = "What is the capital of France?"
    val answers = listOf("Paris", "London", "Berlin", "Madrid")
    val correctAnswer = "Paris"
    Question(
        question = question,
        answers = answers,
        correctAnswer = correctAnswer,
        onAnswerSelected = {}
    )
}
