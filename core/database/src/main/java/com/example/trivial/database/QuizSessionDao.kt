package com.example.trivial.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.trivial.database.model.QuizSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizSessionDao {

    @Upsert
    fun saveQuizSession(quizSession: QuizSessionEntity)

    @Query("SELECT * FROM quiz_session")
    suspend fun getAllQuizSessions(): Flow<List<QuizSessionEntity>>

    @Query("SELECT * FROM quiz_session WHERE id = :id")
    suspend fun getQuizSessionById(id: Long): Flow<QuizSessionEntity>

    @Query("SELECT * FROM quiz_session WHERE category = :category AND difficulty = :difficulty AND type = :type")
    suspend fun filterQuizSessions(category: String, difficulty: String, type: String): Flow<List<QuizSessionEntity>>

    @Query("DELETE FROM quiz_session WHERE id = :id")
    suspend fun deleteQuizSessionById(id: Long)

    @Query("DELETE FROM quiz_session")
    suspend fun purgeQuizSessions()
}