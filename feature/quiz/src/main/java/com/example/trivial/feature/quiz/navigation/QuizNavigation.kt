package com.example.trivial.feature.quiz.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.trivial.feature.quiz.ui.flow.QuizFlowRoute
import com.example.trivial.feature.quiz.ui.setup.QuizRoute
import com.example.trivial.feature.quiz.ui.result.QuizResultRoute
import kotlinx.serialization.Serializable

@Serializable
data object QuizBaseRoute

@Serializable
data object QuizSetupRoute

@Serializable
data class QuizFlowRoute(
    val categoryId: Int,
    val difficulty: String,
    val questionType: String,
)

@Serializable
data class QuizResultRoute(val score: Int, val numberOfQuestions: Int)

fun NavController.navigateToQuizSetup(navOptions: NavOptions? = null) =
    navigate(QuizSetupRoute, navOptions)

fun NavController.navigateToQuizFlow(
    categoryId: Int,
    difficulty: String,
    questionType: String,
    navOptions: NavOptions? = null
) = navigate(
    route = QuizFlowRoute(
        categoryId = categoryId,
        difficulty = difficulty,
        questionType = questionType
    ),
    navOptions = navOptions,
)

fun NavController.navigateToQuizResult(
    score: Int,
    numberOfQuestions: Int,
    navOptions: NavOptions? = null
) = navigate(QuizResultRoute(score, numberOfQuestions), navOptions)

fun NavGraphBuilder.quizGraph(
    startQuiz: (categoryId: Int, difficulty: String, questionType: String) -> Unit,
    onQuizFinished: (score: Int, numberOfQuestions: Int) -> Unit,
    goToHome: () -> Unit,
    onBack: () -> Unit,
) {
    navigation<QuizBaseRoute>(startDestination = QuizSetupRoute) {
        composable<QuizSetupRoute>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            QuizRoute(onBack = onBack, startQuiz = startQuiz)
        }
        composable<QuizFlowRoute> { backStackEntry ->
            val quizFlowRoute: QuizFlowRoute = backStackEntry.toRoute()
            QuizFlowRoute(
                categoryId = quizFlowRoute.categoryId,
                difficulty = quizFlowRoute.difficulty,
                questionType = quizFlowRoute.questionType,
                onQuizFinished = onQuizFinished
            )
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
