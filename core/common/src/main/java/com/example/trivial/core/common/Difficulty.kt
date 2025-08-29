package com.example.trivial.core.common

sealed class Difficulty {
    object Easy : Difficulty()
    object Medium : Difficulty()
    object Hard : Difficulty()

    companion object {
        fun fromString(difficulty: String): Difficulty {
            return when (difficulty) {
                "easy" -> Easy
                "medium" -> Medium
                "hard" -> Hard
                else -> throw IllegalArgumentException("Invalid difficulty: $difficulty")
            }
        }
    }
}
