package com.example.trivial.feature.quiz.domain.usecase

import com.example.trivial.feature.quiz.domain.repository.QuizRepository
import org.koin.core.annotation.Factory

@Factory
class GetQuizUseCase(private val repository: QuizRepository) {

    suspend operator fun invoke(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ) = repository.getQuiz(
        amount = amount,
        categoryId = categoryId,
        difficulty = difficulty,
        type = type
    )
}