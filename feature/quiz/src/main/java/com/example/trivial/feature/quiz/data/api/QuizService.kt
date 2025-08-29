package com.example.trivial.feature.quiz.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.koin.core.annotation.Single

@Single
class QuizService(private val httpClient: HttpClient) {

    suspend fun getQuiz(
        amount: String,
        categoryId: String,
        difficulty: String,
        type: String
    ): HttpResponse =
        httpClient.get("api.php") {
            url {
                parameters.append("amount", amount)
                parameters.append("category", categoryId)
                parameters.append("difficulty", difficulty)
                parameters.append("type", type)
            }
        }
}