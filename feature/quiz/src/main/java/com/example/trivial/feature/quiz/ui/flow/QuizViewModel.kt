package com.example.trivial.feature.quiz.ui.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivial.core.common.TriviaCategories
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.usecase.GetQuizUseCase
import com.example.trivial.feature.quiz.domain.usecase.SaveQuizResultUseCase
import com.example.trivial.feature.quiz.ui.QuizUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizViewModel(
    private val getQuizUseCase: GetQuizUseCase,
    private val saveQuizResultUseCase: SaveQuizResultUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun onStart(
        categoryId: Int,
        difficulty: String,
        questionType: String,
    ) {
        _uiState.update {
            it.copy(
                questions = getQuizUseCase(),
                selectedDifficulty = TriviaDifficulty.valueOf(difficulty),
                selectedType = TriviaQuestionType.valueOf(questionType),
                selectedCategory = TriviaCategories.list.find { category -> category.id == categoryId }
                    ?: TriviaCategory.DEFAULT,
                isLoading = false,
            )
        }
    }

    fun onCorrectAnswer() {
        _uiState.update { currentState ->
            currentState.copy(score = currentState.score + 1)
        }
    }

    fun onNextQuestion() {
        if (_uiState.value.currentQuestion == _uiState.value.questions.size - 1) {
            viewModelScope.launch {
                saveQuizResultUseCase(
                    score = _uiState.value.score,
                    numberOfQuestions = _uiState.value.questions.size,
                    category = _uiState.value.selectedCategory,
                    difficulty = _uiState.value.selectedDifficulty,
                    type = _uiState.value.selectedType,
                )
            }
            _uiState.update { currentState ->
                currentState.copy(finished = true)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(currentQuestion = currentState.currentQuestion + 1)
            }
        }
    }
}