package com.example.trivial.feature.quiz.data.mapper

import com.example.trivial.core.common.Category
import com.example.trivial.core.common.Difficulty
import com.example.trivial.core.common.QuestionType
import com.example.trivial.feature.quiz.data.network.model.QuizQuestionDto
import com.example.trivial.feature.quiz.domain.model.QuizQuestion
import kotlin.random.Random

fun QuizQuestionDto.toQuizQuestion() = QuizQuestion(
    type = QuestionType.fromString(this.type),
    difficulty = Difficulty.fromString(this.difficulty),
    category = Category(id = Random.nextInt(), name = this.category),
    question = this.question,
    correctAnswer = this.correctAnswer,
    incorrectAnswers = this.incorrectAnswers
)
