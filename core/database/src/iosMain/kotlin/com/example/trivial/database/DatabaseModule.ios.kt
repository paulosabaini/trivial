package com.example.trivial.database

import org.koin.core.annotation.Single

@Single
actual fun createAppDatabase(): AppDatabase {
    return getRoomDatabase(getDatabaseBuilder())
}