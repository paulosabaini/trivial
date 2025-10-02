package com.example.trivial.feature.quiz.data.mapper

import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.data.network.model.QuestionDto
import com.example.trivial.feature.quiz.domain.model.Question
import java.net.URLDecoder
import kotlin.random.Random

private const val ENCODING = "UTF-8"

fun QuestionDto.toQuestion() = Question(
    type = TriviaQuestionType.fromString(this.type),
    difficulty = TriviaDifficulty.fromString(this.difficulty),
    category = TriviaCategory(id = Random.nextInt(), name = this.category),
    question = URLDecoder.decode(this.question, ENCODING),
    correctAnswer = URLDecoder.decode(this.correctAnswer, ENCODING),
    incorrectAnswers = this.incorrectAnswers.map { URLDecoder.decode(it, ENCODING) }
)
