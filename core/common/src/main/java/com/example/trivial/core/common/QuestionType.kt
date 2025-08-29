package com.example.trivial.core.common

sealed class QuestionType {
    object TrueFalse : QuestionType()
    object MultipleChoice : QuestionType()

    companion object {
        fun fromString(type: String): QuestionType {
            return when (type) {
                "boolean" -> TrueFalse
                "multiple" -> MultipleChoice
                else -> throw IllegalArgumentException("Invalid question type: $type")
            }
        }
    }
}
