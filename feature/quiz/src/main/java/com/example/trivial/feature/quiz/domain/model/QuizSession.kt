package com.example.trivial.feature.quiz.domain.model

data class QuizSession(
    val id: Long,
    val date: String,
    val score: Int,
    val numberOfQuestions: Int,
    val category: String,
    val difficulty: String,
    val type: String,
)
