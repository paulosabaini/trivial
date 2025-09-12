package com.example.trivial.core.common

enum class TriviaQuestionType(val description: String) {
    TRUE_FALSE("True / False"), MULTIPLE_CHOICE("Multiple Choice");

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
