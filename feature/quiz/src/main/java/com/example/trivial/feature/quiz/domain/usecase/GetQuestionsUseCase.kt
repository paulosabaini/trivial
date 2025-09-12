package com.example.trivial.feature.quiz.domain.usecase

import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Factory

@Factory
class GetQuestionsUseCase(private val repository: TriviaRepository) {

    suspend operator fun invoke(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ) = repository.getQuestions(
        amount = amount,
        categoryId = categoryId,
        difficulty = difficulty,
        type = type
    )
}