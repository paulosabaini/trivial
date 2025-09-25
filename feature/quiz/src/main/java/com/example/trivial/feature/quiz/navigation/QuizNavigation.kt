package com.example.trivial.feature.quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.trivial.feature.quiz.ui.FlowScreen
import com.example.trivial.feature.quiz.ui.QuizRoute
import com.example.trivial.feature.quiz.ui.ResultScreen
import kotlinx.serialization.Serializable

@Serializable
data object QuizBaseRoute

@Serializable
data object QuizSetupRoute

@Serializable
data object QuizFlowRoute

@Serializable
data object QuizResultRoute

fun NavController.navigateToQuizSetup(navOptions: NavOptions? = null) =
    navigate(QuizSetupRoute, navOptions)

fun NavController.navigateToQuizFlow(navOptions: NavOptions? = null) =
    navigate(QuizFlowRoute, navOptions)

fun NavController.navigateToQuizResult(navOptions: NavOptions? = null) =
    navigate(QuizResultRoute, navOptions)

fun NavGraphBuilder.quizGraph(startQuiz: () -> Unit) {
    navigation<QuizBaseRoute>(startDestination = QuizSetupRoute) {
        composable<QuizSetupRoute>() {
            QuizRoute(startQuiz = startQuiz)
        }
        composable<QuizFlowRoute>() {
            FlowScreen()
        }
        composable<QuizResultRoute>() {
            ResultScreen()
        }
    }
}
