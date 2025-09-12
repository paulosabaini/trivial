package com.example.trivial.core.common

sealed class TriviaQuestionType {
    object TrueFalse : TriviaQuestionType()
    object MultipleChoice : TriviaQuestionType()

    companion object {
        fun fromString(type: String): TriviaQuestionType {
            return when (type) {
                "boolean" -> TrueFalse
                "multiple" -> MultipleChoice
                else -> throw IllegalArgumentException("Invalid question type: $type")
            }
        }
    }
}
