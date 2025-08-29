package com.example.trivial.feature.quiz.data.repository

import com.example.trivial.feature.quiz.data.QuizRemoteDataSource
import com.example.trivial.feature.quiz.domain.model.QuizQuestion
import com.example.trivial.feature.quiz.domain.repository.QuizRepository

class QuizRepositoryImpl(private val quizRemoteDataSource: QuizRemoteDataSource) : QuizRepository {
    override suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<QuizQuestion>> {
        return quizRemoteDataSource.getQuiz(amount, categoryId, difficulty, type)
    }
}
