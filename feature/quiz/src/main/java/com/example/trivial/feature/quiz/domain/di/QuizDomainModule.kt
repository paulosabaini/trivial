package com.example.trivial.feature.quiz.domain.di

import com.example.trivial.feature.quiz.data.di.QuizDataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [QuizDataModule::class])
@ComponentScan("com.example.trivial.feature.quiz.domain")
class QuizDomainModule