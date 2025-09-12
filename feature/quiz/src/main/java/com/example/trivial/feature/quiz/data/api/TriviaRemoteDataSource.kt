package com.example.trivial.feature.quiz.data.api

import com.example.trivial.feature.quiz.domain.model.Question

interface TriviaRemoteDataSource {
    suspend fun getQuestions(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<Question>>
}