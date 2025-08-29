package com.example.trivial.feature.quiz.domain.model

import com.example.trivial.core.common.Category
import com.example.trivial.core.common.Difficulty
import com.example.trivial.core.common.QuestionType

data class QuizQuestion(
    val type: QuestionType,
    val difficulty: Difficulty,
    val category: Category,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
