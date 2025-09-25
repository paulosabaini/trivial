package com.example.trivial.core.common

enum class TriviaQuestionType(val description: String) {
    TRUE_FALSE("True / False"), MULTIPLE_CHOICE("Multiple Choice");

    fun toParameterString(): String {
        return when (this) {
            TRUE_FALSE -> "boolean"
            MULTIPLE_CHOICE -> "multiple"
        }
    }

    companion object {
        fun fromString(type: String): TriviaQuestionType {
            return when (type.lowercase()) {
                "boolean", "true / false" -> TRUE_FALSE
                "multiple", "multiple choice" -> MULTIPLE_CHOICE
                else -> throw IllegalArgumentException("Invalid question type: $type")
            }
        }
    }
}
