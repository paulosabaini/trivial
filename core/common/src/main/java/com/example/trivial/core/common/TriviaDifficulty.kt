package com.example.trivial.core.common

sealed class TriviaDifficulty {
    object Easy : TriviaDifficulty()
    object Medium : TriviaDifficulty()
    object Hard : TriviaDifficulty()

    companion object {
        fun fromString(difficulty: String): TriviaDifficulty {
            return when (difficulty) {
                "easy" -> Easy
                "medium" -> Medium
                "hard" -> Hard
                else -> throw IllegalArgumentException("Invalid difficulty: $difficulty")
            }
        }
    }
}
