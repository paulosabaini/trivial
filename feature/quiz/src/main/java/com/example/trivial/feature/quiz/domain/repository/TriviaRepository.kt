package com.example.trivial.feature.quiz.domain.repository

import com.example.trivial.feature.quiz.domain.model.Question

interface TriviaRepository {
    suspend fun getQuestions(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<Question>>
}
