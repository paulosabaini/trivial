package com.example.trivial.feature.quiz.domain.usecase

import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Factory

@Factory
class GetQuestionsUseCase(private val repository: TriviaRepository) {

    suspend operator fun invoke(
        amount: Int,
        categoryId: Int,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ) = repository.getQuestions(
        amount = amount,
        categoryId = categoryId,
        difficulty = difficulty,
        type = type
    )
}