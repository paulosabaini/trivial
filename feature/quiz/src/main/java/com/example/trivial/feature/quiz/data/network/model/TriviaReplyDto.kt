package com.example.trivial.feature.quiz.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaReplyDto(
    @SerialName("response_code")
    val responseCode: Int,
    val results: List<QuestionDto>
)
