package com.example.trivial.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_session")
data class QuizSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val date: Long,
    val score: Int,
    val numberOfQuestions: Int,
    val categoryId: Int,
    val difficulty: String,
    val type: String,
)