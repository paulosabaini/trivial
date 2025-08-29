package com.example.trivial.feature.quiz.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizReplyDto(
    @SerialName("response_code")
    val responseCode: Int,
    val results: List<QuizQuestionDto>
)
