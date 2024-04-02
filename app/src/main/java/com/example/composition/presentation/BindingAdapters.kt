package com.example.composition.presentation

import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text =
        String.format(
            textView.context.resources.getString(R.string.required_score),
            count.toString()
        )
}

@BindingAdapter("countOfRightAnswers")
fun bindCountOfRightAnswers(textView: TextView, count: Int) {
    textView.text =
        String.format(
            textView.context.getString(R.string.score_answers),
            count.toString()
        )
}

@BindingAdapter("minPercentageOfRightAnswers")
fun bindMinPercentageOfRightAnswers(textView: TextView, count: Int) {
    textView.text =
        String.format(
            textView.context.getString(R.string.required_percentage),
            count.toString()
        )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text =
        String.format(
            textView.context.getString(R.string.score_percentage),
            gameResult.run {
                ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
            }
        )
}

@BindingAdapter("setEmojiResult")
fun bindSetEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageDrawable(
        ContextCompat.getDrawable(
            imageView.context,
            if (winner) R.drawable.ic_smile else R.drawable.ic_sad
        )
    )
}

