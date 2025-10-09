package com.example.trivial.feature.quiz.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.trivial.feature.quiz.ui.QuizFlowRoute
import com.example.trivial.feature.quiz.ui.QuizRoute
import com.example.trivial.feature.quiz.ui.QuizViewModel
import com.example.trivial.feature.quiz.ui.QuizResultRoute
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.navigation.koinNavViewModel

@Serializable
data object QuizBaseRoute

@Serializable
data object QuizSetupRoute

@Serializable
data object QuizFlowRoute

@Serializable
data class QuizResultRoute(val score: Int, val numberOfQuestions: Int)

fun NavController.navigateToQuizSetup(navOptions: NavOptions? = null) =
    navigate(QuizSetupRoute, navOptions)

fun NavController.navigateToQuizFlow(navOptions: NavOptions? = null) =
    navigate(QuizFlowRoute, navOptions)

fun NavController.navigateToQuizResult(
    score: Int,
    numberOfQuestions: Int,
    navOptions: NavOptions? = null
) =
    navigate(QuizResultRoute(score, numberOfQuestions), navOptions)

// TODO: Verify if the NavController can be removed or if the navigation could be handled inside this graph.
fun NavGraphBuilder.quizGraph(
    navController: NavController,
    startQuiz: () -> Unit,
    onQuizFinished: (score: Int, numberOfQuestions: Int) -> Unit,
    goToHome: () -> Unit
) {
    navigation<QuizBaseRoute>(startDestination = QuizSetupRoute) {
        composable<QuizSetupRoute> { backStackEntry ->
            val viewModelStoreOwner = remember(backStackEntry) {
                navController.getBackStackEntry(QuizBaseRoute)
            }
            val viewModel: QuizViewModel =
                koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner)
            QuizRoute(viewModel = viewModel, startQuiz = startQuiz)
        }
        composable<QuizFlowRoute> { backStackEntry ->
            val viewModelStoreOwner = remember(backStackEntry) {
                navController.getBackStackEntry(QuizBaseRoute)
            }
            val viewModel: QuizViewModel =
                koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner)
            QuizFlowRoute(viewModel = viewModel, onQuizFinished = onQuizFinished)
        }
        composable<QuizResultRoute> { backStackEntry ->
            val quizResultRoute: QuizResultRoute = backStackEntry.toRoute()
            QuizResultRoute(
                score = quizResultRoute.score,
                numberOfQuestions = quizResultRoute.numberOfQuestions,
                onContinue = goToHome
            )
        }
    }
}
