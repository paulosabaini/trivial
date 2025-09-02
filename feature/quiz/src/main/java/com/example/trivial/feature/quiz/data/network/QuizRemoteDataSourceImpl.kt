package com.example.trivial.feature.quiz.data.network

import com.example.trivial.feature.quiz.data.api.QuizRemoteDataSource
import com.example.trivial.feature.quiz.data.mapper.toQuizQuestion
import com.example.trivial.feature.quiz.data.network.model.QuizReplyDto
import com.example.trivial.feature.quiz.domain.model.QuizQuestion
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.koin.core.annotation.Single

@Single(binds = [QuizRemoteDataSource::class])
class QuizRemoteDataSourceImpl(private val quizService: QuizService) : QuizRemoteDataSource {

    override suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): Result<List<QuizQuestion>> {
        val response = quizService.getQuiz(amount, categoryId, difficulty, type)

        // TODO: Improve error handling and validate responseCode
        if (response.status.isSuccess()) {
            val body = response.body<QuizReplyDto>()
            val questions = body.results.map { it.toQuizQuestion() }
            return Result.success(questions)
        } else {
            return Result.failure(Exception(response.status.description))
        }
    }
}