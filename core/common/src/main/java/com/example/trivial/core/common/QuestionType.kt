package com.example.trivial.core.common

sealed class QuestionType {
    object TrueFalse : QuestionType()
    object MultipleChoice : QuestionType()
}
