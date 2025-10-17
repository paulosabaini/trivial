package com.example.trivial.feature.quiz.domain.usecase

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Factory

@Factory
class SaveQuizResultUseCase(private val repository: TriviaRepository) {
    suspend operator fun invoke(
        score: Int,
        numberOfQuestions: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ) {
        repository.saveQuizResult(
            score = score,
            numberOfQuestions = numberOfQuestions,
            category = category,
            difficulty = difficulty,
            type = type
        )
    }
}