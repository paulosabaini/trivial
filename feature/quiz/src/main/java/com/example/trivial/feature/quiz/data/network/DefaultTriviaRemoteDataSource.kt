package com.example.trivial.feature.quiz.data.network

import com.example.trivial.feature.quiz.data.api.TriviaRemoteDataSource
import com.example.trivial.feature.quiz.data.mapper.toQuestion
import com.example.trivial.feature.quiz.data.network.model.TriviaReplyDto
import com.example.trivial.feature.quiz.domain.model.Question
import com.example.trivial.network.ResponseException
import com.example.trivial.network.toResult
import org.koin.core.annotation.Single

@Single(binds = [TriviaRemoteDataSource::class])
class DefaultTriviaRemoteDataSource(private val triviaApiService: TriviaApiService) : TriviaRemoteDataSource {

    override suspend fun getQuestions(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<Question>> {

        return triviaApiService
            .getQuestions(amount, categoryId, difficulty, type)
            .toResult<TriviaReplyDto>()
            .map { body ->
                return when (body.responseCode) {
                    0 -> Result.success(body.results.map { it.toQuestion() })
                    1 -> Result.failure(ResponseException.NoResults())
                    2 -> Result.failure(ResponseException.InvalidParameter())
                    3 -> Result.failure(ResponseException.TokenNotFound())
                    4 -> Result.failure(ResponseException.TokenEmpty())
                    5 -> Result.failure(ResponseException.RateLimit())
                    else -> Result.failure(ResponseException.Unknown("Unknown API error with code: ${body.responseCode}"))
                }
            }
    }
}