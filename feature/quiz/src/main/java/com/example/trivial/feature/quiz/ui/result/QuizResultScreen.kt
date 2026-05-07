package com.example.trivial.feature.quiz.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        Text(
            text = "★",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 80.sp),
            color = TrivialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "QUIZ COMPLETED",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Light,
                letterSpacing = 4.sp
            ),
            color = TrivialTheme.colors.gray600,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$score / $numberOfQuestions",
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.ExtraLight),
            color = TrivialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "CORRECT ANSWERS",
            style = MaterialTheme.typography.labelSmall,
            color = TrivialTheme.colors.gray500,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
        
        TrivialButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(TrivialSize.SizeExtraExtraLarge),
            text = "CONTINUE",
            onClick = onContinue
        )
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {
    TrivialTheme {
        QuizResultScreen(score = 8, numberOfQuestions = 10, onContinue = {})
    }
}
