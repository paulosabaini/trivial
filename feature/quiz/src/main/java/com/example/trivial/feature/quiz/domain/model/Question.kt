package com.example.trivial.feature.quiz.domain.model

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType

data class Question(
    val type: TriviaQuestionType,
    val difficulty: TriviaDifficulty,
    val category: TriviaCategory,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
