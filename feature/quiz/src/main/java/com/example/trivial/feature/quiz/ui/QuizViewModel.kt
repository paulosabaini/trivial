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

    fun onQuizSetupAction(action: QuizSetupAction) {
        _uiState.update { currentState ->
            when (action) {
                is QuizSetupAction.OnDifficultyChanged -> currentState.copy(selectedDifficulty = action.difficulty)
                is QuizSetupAction.OnTypeChanged -> currentState.copy(selectedType = action.type)
                is QuizSetupAction.OnAmountChanged -> currentState.copy(numberOfQuestions = action.amount)
                is QuizSetupAction.OnCategoryChanged -> currentState.copy(selectedCategory = action.category)
            }
        }
    }
}