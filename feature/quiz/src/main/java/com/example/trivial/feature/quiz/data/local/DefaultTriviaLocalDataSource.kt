package com.example.trivial.feature.quiz.data.local

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.database.QuizSessionDao
import com.example.trivial.database.model.QuizSessionEntity
import com.example.trivial.feature.quiz.data.api.TriviaLocalDataSource
import org.koin.core.annotation.Single

// TODO: Inject IO Context
// TODO: Check if injecting DAO is the best way to do this
@Single(binds = [TriviaLocalDataSource::class])
class DefaultTriviaLocalDataSource(private val quizSessionDao: QuizSessionDao) : TriviaLocalDataSource {
    override suspend fun saveQuizResult(
        score: Int,
        numberOfQuestions: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ) {
        quizSessionDao.saveQuizSession(
            QuizSessionEntity(
                date = System.currentTimeMillis(),
                score = score,
                numberOfQuestions = numberOfQuestions,
                categoryId = category.id,
                difficulty = difficulty.description,
                type = type.description,
            )
        )
    }
}