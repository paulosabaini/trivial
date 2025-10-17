package com.example.trivial.feature.quiz.data.api

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType

interface TriviaLocalDataSource {
    suspend fun saveQuizResult(
        score: Int,
        numberOfQuestions: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    )
}