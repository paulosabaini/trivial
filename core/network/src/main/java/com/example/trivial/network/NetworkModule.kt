package com.example.trivial.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

private const val BASE_URL = "https://opentdb.com/"
private const val TIMEOUT = 15000

@Module
@ComponentScan
class NetworkModule

@Single
fun provideHttpClient(): HttpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    engine {
        connectTimeout = TIMEOUT
        socketTimeout = TIMEOUT
    }

    defaultRequest {
        headers {
            append(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        url(BASE_URL)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.BODY
    }
}