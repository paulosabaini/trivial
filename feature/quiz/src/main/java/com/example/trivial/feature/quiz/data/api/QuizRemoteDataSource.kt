package com.example.trivial.feature.quiz.data.api

import com.example.trivial.feature.quiz.domain.model.QuizQuestion

interface QuizRemoteDataSource {
    suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<QuizQuestion>>
}