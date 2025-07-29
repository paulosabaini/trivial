package com.example.trivial.feature.quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.trivial.feature.quiz.QuizRoute
import kotlinx.serialization.Serializable

@Serializable
data object QuizRoute

fun NavController.navigateToQuiz(navOptions: NavOptions? = null) =
    navigate(QuizRoute, navOptions)

fun NavGraphBuilder.quizScreen() {
    composable<QuizRoute> {
        QuizRoute()
    }
}