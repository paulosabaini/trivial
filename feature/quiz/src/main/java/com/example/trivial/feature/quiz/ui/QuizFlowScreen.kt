package com.example.trivial.feature.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trivial.ui.components.TrivialQuestion
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme
import kotlinx.coroutines.delay

@Composable
internal fun QuizFlowRoute(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel,
    onQuizFinished: (score: Int, numberOfQuestions: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizFlowScreen(
        modifier = modifier,
        uiState = uiState,
        onNextQuestion = viewModel::onNextQuestion,
        onCorrectAnswer = viewModel::onCorrectAnswer,
        onQuizFinished = onQuizFinished
    )
}

@Composable
internal fun QuizFlowScreen(
    modifier: Modifier = Modifier,
    uiState: QuizUiState,
    onNextQuestion: () -> Unit,
    onCorrectAnswer: () -> Unit,
    onQuizFinished: (score: Int, numberOfQuestions: Int) -> Unit,
) {
    var waitBeforeNextQuestion by remember { mutableStateOf(false) }

    if (uiState.finished) {
        onQuizFinished(uiState.score, uiState.questions.size)
    }

    // TODO: Replace this logic with animation
    LaunchedEffect(waitBeforeNextQuestion) {
        if (waitBeforeNextQuestion) {
            delay(2000)
            waitBeforeNextQuestion = false
            onNextQuestion()
        }
    }

    // TODO: Display countdown before starting the quiz
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TrivialTheme.colors.background),
        verticalArrangement = Arrangement.Center
    ) {
        val question = uiState.questions[uiState.currentQuestion]
        key(question) {
            // TODO: Find a better way to shuffle the answers
            val answers by remember {
                derivedStateOf {
                    getMixedAnswers(
                        question.correctAnswer,
                        question.incorrectAnswers
                    )
                }
            }
            TrivialQuestion(
                modifier = Modifier.padding(TrivialSize.SizeMedium),
                question = question.question,
                answers = answers,
                correctAnswer = question.correctAnswer,
                totalTimeSeconds = 15,
                onCorrectAnswer = {
                    // TODO: Display correct answer animation
                    onCorrectAnswer()
                    // onNextQuestion()
                    waitBeforeNextQuestion = true
                },
                onWrongAnswer = {
                    // TODO: Display wrong answer animation
                    // onNextQuestion()
                    waitBeforeNextQuestion = true
                },
                onTimeUp = {
                    // TODO: Display time up animation
                    // onNextQuestion()
                    waitBeforeNextQuestion = true
                }
            )
        }
    }
}

private fun getMixedAnswers(correctAnswer: String, incorrectAnswers: List<String>): List<String> {
    return (incorrectAnswers + correctAnswer).shuffled()
}