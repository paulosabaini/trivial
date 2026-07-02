package com.example.trivial.database

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class DatabaseModule

@Single
expect fun createAppDatabase(): AppDatabase

@Single
internal fun createQuizSessionDao(appDatabase: AppDatabase) = appDatabase.quizSessionDao()
