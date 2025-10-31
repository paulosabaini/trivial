package com.example.trivial.network

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class ApiException(val code: Int, override val message: String) : Exception(message)

suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return if (status.isSuccess()) {
        try {
            val body = this.body<T>()
            Result.success(body)
        } catch (e: NoTransformationFoundException) {
            Result.failure(ApiException(status.value, "Failed to parse response: ${e.message}"))
        }
    } else {
        Result.failure(ApiException(status.value, this.bodyAsText()))
    }
}
