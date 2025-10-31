package com.example.trivial.network

sealed class ResponseException : Exception() {
    data class NoResults(override val message: String? = null) : ResponseException()
    data class InvalidParameter(override val message: String? = null) : ResponseException()
    data class TokenNotFound(override val message: String? = null) : ResponseException()
    data class TokenEmpty(override val message: String? = null) : ResponseException()
    data class RateLimit(override val message: String? = null) : ResponseException()
    data class Unknown(override val message: String) : ResponseException()
}
