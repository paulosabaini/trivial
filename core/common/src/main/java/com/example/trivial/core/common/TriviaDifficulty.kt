package com.example.trivial.core.common

enum class TriviaDifficulty(val description: String) {
    EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

    companion object {
        fun fromString(difficulty: String): TriviaDifficulty {
            return when (difficulty.lowercase()) {
                "easy" -> EASY
                "medium" -> MEDIUM
                "hard" -> HARD
                else -> throw IllegalArgumentException("Invalid difficulty: $difficulty")
            }
        }
    }
}
