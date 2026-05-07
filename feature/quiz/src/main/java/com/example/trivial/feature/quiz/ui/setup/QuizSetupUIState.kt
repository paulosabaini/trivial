package com.example.trivial.feature.quiz.ui.setup

import androidx.compose.runtime.Immutable
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType

@Immutable
data class QuizSetupUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedDifficulty: TriviaDifficulty = TriviaDifficulty.MEDIUM,
    val selectedCategory: TriviaCategory = TriviaCategory.DEFAULT,
    val selectedType: TriviaQuestionType = TriviaQuestionType.MULTIPLE_CHOICE,
    val numberOfQuestions: Int = 10,
    val isReadyToPlay: Boolean = false,
    val isCategoryBottomSheetVisible: Boolean = false,
)
