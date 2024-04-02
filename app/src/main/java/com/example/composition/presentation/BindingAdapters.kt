package com.example.composition.presentation

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClick {
    fun click(number: Int)
}

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

@BindingAdapter("enoughCountOfRightAnswers")
fun bindEnoughCountOfRightAnswers(textView: TextView, enoughCount: Boolean) {

    textView.setTextColor(getColorByState(textView, enoughCount))
}

@BindingAdapter("enoughPercentOfRightAnswers")
fun bindEnoughPercentOfRightAnswers(progressBar: ProgressBar, enoughCount: Boolean) {

    progressBar.progressTintList = ColorStateList.valueOf(getColorByState(progressBar, enoughCount))
}

fun getColorByState(view: View, enoughCount: Boolean): Int {
    val colorId = if (enoughCount) android.R.color.holo_green_light
    else android.R.color.holo_red_light
    return view.context.getColor(colorId)
}

@BindingAdapter("numberToString")
fun bindNumberToString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, onOptionClick: OnOptionClick) {
    textView.setOnClickListener {
        onOptionClick.click(textView.text.toString().toInt())
    }
}





