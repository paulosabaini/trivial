package com.example.trivial.feature.quiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivial.network.ResponseException
import com.example.trivial.feature.quiz.domain.usecase.GetQuestionsUseCase
import com.example.trivial.feature.quiz.domain.usecase.SaveQuizResultUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizViewModel(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val saveQuizResultUseCase: SaveQuizResultUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    // TODO: Retrieve categories from API and validate number of questions available.
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

    fun onPlayClick() {
        _uiState.update { currentState ->
            currentState.copy(isLoading = true)
        }

        viewModelScope.launch {
            getQuestionsUseCase(
                amount = _uiState.value.numberOfQuestions,
                categoryId = _uiState.value.selectedCategory.id,
                difficulty = _uiState.value.selectedDifficulty,
                type = _uiState.value.selectedType,
            ).fold(
                onSuccess = { questions ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = null,
                            isReadyToPlay = true,
                            questions = questions
                        )
                    }
                },
                onFailure = {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isReadyToPlay = false,
                            error = when (it) {
                                is ResponseException.NoResults -> "No questions found for the selected criteria"
                                is ResponseException.InvalidParameter -> "Invalid parameter"
                                is ResponseException.RateLimit -> "Rate limit exceeded"
                                is ResponseException.Unknown -> "Ops, something went wrong, please try again"
                                else -> "Ops, something went wrong, please try again"
                            }
                        )
                    }
                }
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
