package com.example.trivial.feature.quiz.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivial.feature.quiz.domain.usecase.GetQuestionsUseCase
import com.example.trivial.network.ResponseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizSetupViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizSetupUIState())
    val uiState: StateFlow<QuizSetupUIState> = _uiState.asStateFlow()

    // TODO: Retrieve categories from API and validate number of questions available.
    fun onQuizSetupAction(action: QuizSetupAction) {
        _uiState.update { currentState ->
            when (action) {
                is QuizSetupAction.OnDifficultyChanged -> currentState.copy(selectedDifficulty = action.difficulty)
                is QuizSetupAction.OnTypeChanged -> currentState.copy(selectedType = action.type)
                is QuizSetupAction.OnAmountChanged -> currentState.copy(numberOfQuestions = action.amount)
                is QuizSetupAction.OnCategoryChanged -> currentState.copy(selectedCategory = action.category)
                is QuizSetupAction.OpenCategoryBottomSheet -> currentState.copy(isCategoryBottomSheetVisible = true)
                is QuizSetupAction.DismissCategoryBottomSheet -> currentState.copy(isCategoryBottomSheetVisible = false)
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
                onSuccess = {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = null,
                            isReadyToPlay = true,
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
}
