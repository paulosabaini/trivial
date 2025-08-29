package com.example.trivial.feature.quiz.domain.repository

import com.example.trivial.feature.quiz.domain.model.QuizQuestion

interface QuizRepository {
    suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<QuizQuestion>>
}
