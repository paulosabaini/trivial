package com.example.trivial.feature.quiz.domain.repository

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.domain.model.Question

interface TriviaRepository {
    suspend fun getQuestions(
        amount: Int,
        categoryId: Int,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ): Result<List<Question>>

    suspend fun saveQuizResult(
        score: Int,
        numberOfQuestions: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    )

    fun getQuiz(): List<Question>
}
