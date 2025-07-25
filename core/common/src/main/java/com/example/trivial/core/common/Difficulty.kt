package com.example.trivial.core.common

sealed class Difficulty {
    object Easy : Difficulty()
    object Medium : Difficulty()
    object Hard : Difficulty()
}
