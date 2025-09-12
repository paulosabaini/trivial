package com.example.trivial.feature.quiz.data.network

import com.example.trivial.feature.quiz.data.api.TriviaRemoteDataSource
import com.example.trivial.feature.quiz.data.mapper.toQuestion
import com.example.trivial.feature.quiz.data.network.model.TriviaReplyDto
import com.example.trivial.feature.quiz.domain.model.Question
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.koin.core.annotation.Single

@Single(binds = [TriviaRemoteDataSource::class])
class DefaultTriviaRemoteDataSource(private val triviaApiService: TriviaApiService) : TriviaRemoteDataSource {

    override suspend fun getQuestions(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<Question>> {
        val response = triviaApiService.getQuestions(amount, categoryId, difficulty, type)

        // TODO: Improve error handling and validate responseCode
        if (response.status.isSuccess()) {
            val body = response.body<TriviaReplyDto>()
            val questions = body.results.map { it.toQuestion() }
            return Result.success(questions)
        } else {
            return Result.failure(Exception(response.status.description))
        }
    }
}