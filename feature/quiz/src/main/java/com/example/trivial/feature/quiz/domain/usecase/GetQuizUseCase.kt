package com.example.trivial.feature.quiz.domain.usecase

import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Factory

@Factory
class GetQuizUseCase(private val repository: TriviaRepository) {
    operator fun invoke() = repository.getQuiz()
}