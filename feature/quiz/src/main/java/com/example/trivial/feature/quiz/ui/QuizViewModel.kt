package com.example.trivial.feature.quiz.ui

import androidx.lifecycle.ViewModel
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.usecase.GetQuestionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun onDifficultyChanged(difficulty: TriviaDifficulty) {
        _uiState.update { it.copy(selectedDifficulty = difficulty) }
    }

    fun onTypeChanged(type: TriviaQuestionType) {
        _uiState.update { it.copy(selectedType = type) }
    }

    fun onAmountChanged(amount: Int) {
        _uiState.update { it.copy(numberOfQuestions = amount) }
    }

    fun onCategoryChanged(category: TriviaCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }
}