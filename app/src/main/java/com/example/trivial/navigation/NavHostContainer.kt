package com.example.trivial.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.trivial.feature.quiz.navigation.navigateToQuizFlow
import com.example.trivial.feature.quiz.navigation.navigateToQuizSetup
import com.example.trivial.feature.quiz.navigation.quizGraph
import com.example.trivial.home.navigation.HomeRoute
import com.example.trivial.home.navigation.homeScreen

@Composable
fun NavHostContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier.padding(paddingValues)
    ) {
        homeScreen(
            onNavigateToQuiz = { navController.navigateToQuizSetup() },
            onNavigateToHistory = { navController.navigate(route = "history") },
            onNavigateToSettings = { navController.navigate(route = "settings") }
        )
        quizGraph(
            startQuiz = { navController.navigateToQuizFlow() }
        )
    }
}
