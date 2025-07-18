package com.example.trivial.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trivial.database.model.QuizSessionEntity

@Database(
    entities = [QuizSessionEntity::class],
    version = AppDatabase.DB_VERSION,
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun quizSessionDao(): QuizSessionDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app_database"
    }
}