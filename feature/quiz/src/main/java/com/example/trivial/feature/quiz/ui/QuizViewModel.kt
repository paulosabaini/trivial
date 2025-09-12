package com.example.trivial.feature.quiz.ui

import androidx.lifecycle.ViewModel
import com.example.trivial.feature.quiz.domain.usecase.GetQuestionsUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

}