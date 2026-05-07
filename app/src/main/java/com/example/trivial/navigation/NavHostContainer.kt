package com.example.trivial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.trivial.feature.home.navigation.HomeRoute
import com.example.trivial.feature.home.navigation.homeScreen
import com.example.trivial.feature.home.navigation.navigateToHome
import com.example.trivial.feature.quiz.navigation.navigateToQuizFlow
import com.example.trivial.feature.quiz.navigation.navigateToQuizResult
import com.example.trivial.feature.quiz.navigation.navigateToQuizSetup
import com.example.trivial.feature.quiz.navigation.quizGraph

@Composable
fun NavHostContainer() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        homeScreen(
            onNavigateToQuiz = { navController.navigateToQuizSetup() },
            onNavigateToHistory = { navController.navigate(route = "history") },
            onNavigateToSettings = { navController.navigate(route = "settings") }
        )
        quizGraph(
            startQuiz = { categoryId, difficulty, questionType ->
                navController.navigateToQuizFlow(
                    categoryId = categoryId,
                    difficulty = difficulty,
                    questionType = questionType
                )
            },
            onQuizFinished = { score, numberOfQuestions ->
                navController.navigateToQuizResult(
                    score,
                    numberOfQuestions
                )
            },
            goToHome = { navController.navigateToHome() },
            onBack = { navController.popBackStack() }
        )
    }
}
