package com.example.trivial.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_session")
data class QuizSessionEntity(
    @PrimaryKey
    val id: Long,
    val date: String,
    val score: Int,
    val numberOfQuestions: Int,
    val category: String,
    val difficulty: String,
    val type: String,
)