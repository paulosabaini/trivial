package com.example.trivial.feature.quiz.ui

import androidx.compose.runtime.Immutable
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType

@Immutable
sealed interface QuizSetupAction {
    data class OnDifficultyChanged(val difficulty: TriviaDifficulty) : QuizSetupAction
    data class OnTypeChanged(val type: TriviaQuestionType) : QuizSetupAction
    data class OnAmountChanged(val amount: Int) : QuizSetupAction
    data class OnCategoryChanged(val category: TriviaCategory) : QuizSetupAction
}