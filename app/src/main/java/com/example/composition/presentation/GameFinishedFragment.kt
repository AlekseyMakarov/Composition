package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")
    private var _binding: FragmentGameFinishedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        binding.tvRequiredAnswers.apply {
            text = String.format(text.toString(), gameResult.gameSettings.minCountOfRightAnswers)
        }
        binding.tvRequiredPercentage.apply {
            text = String.format(
                text.toString(),
                gameResult.gameSettings.minPercentageOfRightAnswers
            )
        }
        binding.tvScoreAnswers.apply {
            text = String.format(
                text.toString(),
                gameResult.countOfRightAnswers
            )
        }
        binding.tvScorePercentage.apply {
            text = String.format(
                text.toString(),
                gameResult.run {
                    ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
                }
            )
        }
        binding.emojiResult.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                if (gameResult.winner) R.drawable.ic_smile else R.drawable.ic_sad
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        gameResult = requireArguments().getParcelable<GameResult>(GAME_RESULT_KEY)!!
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    companion object {
        const val GAME_RESULT_KEY = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }
    }
}
