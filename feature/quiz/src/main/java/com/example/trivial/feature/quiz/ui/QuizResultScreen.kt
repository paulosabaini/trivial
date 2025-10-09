package com.example.trivial.feature.quiz.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.feature.quiz.R
import com.example.trivial.ui.components.TrivialButton
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme

@Composable
internal fun QuizResultRoute(
    modifier: Modifier = Modifier,
    score: Int,
    numberOfQuestions: Int,
    onContinue: () -> Unit
) {
    QuizResultScreen(
        modifier = modifier,
        score = score,
        numberOfQuestions = numberOfQuestions,
        onContinue = onContinue
    )
}

@Composable
internal fun QuizResultScreen(
    modifier: Modifier = Modifier,
    score: Int,
    numberOfQuestions: Int,
    onContinue: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TrivialTheme.colors.background)
            .padding(TrivialSize.SizeMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(TrivialSize.SizeExtraHuge),
            painter = painterResource(id = R.drawable.ic_trophy),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(TrivialSize.SizeLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "CONGRATULATIONS",
            style = MaterialTheme.typography.headlineMedium.copy(color = TrivialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(TrivialSize.SizeLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "YOUR SCORE",
            style = MaterialTheme.typography.headlineSmall.copy(color = TrivialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(TrivialSize.SizeSmall))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$score/$numberOfQuestions",
            style = MaterialTheme.typography.headlineLarge.copy(color = TrivialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(TrivialSize.SizeExtraExtraLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "YOU DID A GREAT JOB, LEARN MORE BY TAKING ANOTHER QUIZ",
            style = MaterialTheme.typography.bodyLarge.copy(color = TrivialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        TrivialButton(text = "CONTINUE", onClick = onContinue)
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {
    TrivialTheme {
        QuizResultScreen(score = 8, numberOfQuestions = 10, onContinue = {})
    }
}