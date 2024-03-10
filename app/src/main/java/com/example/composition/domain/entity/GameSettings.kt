package com.example.composition.domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val minSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentageOfRightAnswers: Int,
    val minTimeInSeconds: Int
    ): Parcelable