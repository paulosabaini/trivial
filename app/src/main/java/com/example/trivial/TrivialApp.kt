package com.example.trivial

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant

class TrivialApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TrivialApp)
        }

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}