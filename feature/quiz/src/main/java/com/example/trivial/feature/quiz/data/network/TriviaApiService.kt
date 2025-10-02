package com.example.trivial.feature.quiz.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.koin.core.annotation.Single

@Single
class TriviaApiService(private val httpClient: HttpClient) {

    suspend fun getQuestions(
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
                parameters.append("encode", "url3986")
            }
        }
}