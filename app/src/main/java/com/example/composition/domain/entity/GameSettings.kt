package com.example.composition.domain.entity

data class GameSettings (
    val minSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentageOfRightAnswers: Int,
    val minTimeInSeconds: Int
    )