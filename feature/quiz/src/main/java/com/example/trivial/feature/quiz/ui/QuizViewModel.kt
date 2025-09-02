package com.example.trivial.feature.quiz.ui

import androidx.lifecycle.ViewModel
import com.example.trivial.feature.quiz.domain.usecase.GetQuizUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class QuizViewModel(private val getQuizUseCase: GetQuizUseCase) : ViewModel() {

}