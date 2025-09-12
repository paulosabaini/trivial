package com.example.trivial.feature.quiz.data.repository

import com.example.trivial.feature.quiz.data.api.TriviaRemoteDataSource
import com.example.trivial.feature.quiz.domain.model.Question
import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Single

@Single(binds = [TriviaRepository::class])
class TriviaRepositoryImpl(private val triviaRemoteDataSource: TriviaRemoteDataSource) : TriviaRepository {
    override suspend fun getQuestions(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<Question>> {
        return triviaRemoteDataSource.getQuestions(amount, categoryId, difficulty, type)
    }
}
