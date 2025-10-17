package com.example.trivial.database

import android.content.Context
import androidx.room.Room
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class DatabaseModule

@Single
internal fun createAppDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true).build()

@Single
internal fun createQuizSessionDao(appDatabase: AppDatabase) = appDatabase.quizSessionDao()
