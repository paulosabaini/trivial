package com.example.trivial

import android.app.Application
import com.example.trivial.database.DatabaseModule
import com.example.trivial.feature.quiz.data.di.QuizDataModule
import com.example.trivial.feature.quiz.domain.di.QuizDomainModule
import com.example.trivial.feature.quiz.ui.QuizPresentationModule
import com.example.trivial.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber
import timber.log.Timber.Forest.plant

class TrivialApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TrivialApp)

            modules(
                QuizPresentationModule().module,
                QuizDomainModule().module,
                QuizDataModule().module,
                NetworkModule().module,
                DatabaseModule().module
            )
        }

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}