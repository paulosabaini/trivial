package com.example.trivial.database

import android.content.Context
import org.koin.core.annotation.Single
import org.koin.mp.KoinPlatform.getKoin

@Single
actual fun createAppDatabase(): AppDatabase {
    val context = getKoin().get<Context>()
    return getRoomDatabase(getDatabaseBuilder(context))
}
