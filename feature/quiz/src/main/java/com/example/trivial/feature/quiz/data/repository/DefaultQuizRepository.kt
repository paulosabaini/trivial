package com.example.trivial.feature.quiz.data.repository

import com.example.trivial.feature.quiz.data.api.QuizRemoteDataSource
import com.example.trivial.feature.quiz.domain.model.QuizQuestion
import com.example.trivial.feature.quiz.domain.repository.QuizRepository
import org.koin.core.annotation.Single

@Single(binds = [QuizRepository::class])
class DefaultQuizRepository(private val quizRemoteDataSource: QuizRemoteDataSource) : QuizRepository {
    override suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<QuizQuestion>> {
        return quizRemoteDataSource.getQuiz(amount, categoryId, difficulty, type)
    }
}
