package com.example.trivial

import android.app.Application
import com.example.trivial.database.DatabaseModule
import com.example.trivial.feature.quiz.data.di.QuizDataModule
import com.example.trivial.feature.quiz.domain.di.QuizDomainModule
import com.example.trivial.feature.quiz.ui.di.QuizUiModule
import com.example.trivial.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant

@KoinApplication(modules = [QuizUiModule::class, QuizDomainModule::class, QuizDataModule::class, NetworkModule::class, DatabaseModule::class])
class TrivialApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin<TrivialApp> {
            androidLogger()
            androidContext(this@TrivialApp)
        }

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}