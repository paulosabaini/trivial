package com.example.trivial.feature.quiz.ui

import androidx.compose.runtime.Immutable
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.model.Question

@Immutable
data class QuizUiState(
    val selectedDifficulty: TriviaDifficulty = TriviaDifficulty.MEDIUM,
    val selectedCategory: TriviaCategory = TriviaCategory.DEFAULT,
    val selectedType: TriviaQuestionType = TriviaQuestionType.MULTIPLE_CHOICE,
    val numberOfQuestions: Int = 10,
    val isLoading: Boolean = false,
    val error: String? = null,
    val questions: List<Question> = emptyList(),
    val isReadyToPlay: Boolean = false,
    val currentQuestion: Int = 0,
    val score: Int = 0,
    val finished: Boolean = false,
)
