package com.example.trivial.feature.quiz.ui.di

import com.example.trivial.feature.quiz.domain.di.QuizDomainModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([QuizDomainModule::class])
@ComponentScan("com.example.trivial.feature.quiz.ui")
class QuizUiModule