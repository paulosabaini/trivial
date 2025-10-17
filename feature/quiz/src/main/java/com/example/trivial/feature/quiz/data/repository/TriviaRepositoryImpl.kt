package com.example.trivial.feature.quiz.data.repository

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.data.api.TriviaLocalDataSource
import com.example.trivial.feature.quiz.data.api.TriviaRemoteDataSource
import com.example.trivial.feature.quiz.domain.model.Question
import com.example.trivial.feature.quiz.domain.repository.TriviaRepository
import org.koin.core.annotation.Single

@Single(binds = [TriviaRepository::class])
class TriviaRepositoryImpl(
    private val triviaRemoteDataSource: TriviaRemoteDataSource,
    private val triviaLocalDataSource: TriviaLocalDataSource
) :
    TriviaRepository {
    override suspend fun getQuestions(
        amount: Int,
        categoryId: Int,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ): Result<List<Question>> {
        return triviaRemoteDataSource.getQuestions(
            amount = amount.toString(),
            categoryId = categoryId.toString(),
            difficulty = difficulty.toParameterString(),
            type = type.toParameterString(),
        )
    }

    override suspend fun saveQuizResult(
        score: Int,
        numberOfQuestions: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaQuestionType
    ) {
        triviaLocalDataSource.saveQuizResult(score, numberOfQuestions, category, difficulty, type)
    }
}
