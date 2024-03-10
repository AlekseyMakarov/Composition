package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult
    private val binding: FragmentGameFinishedBinding
    get() = _binding?: throw RuntimeException("FragmentGameFinishedBinding is null")
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
        backPressed()
        binding.buttonRetry.setOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack(GameFragment.GAME_FRAGMENT_TAG, POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(){
        gameResult = requireArguments().getParcelable<GameResult>(GAME_RESULT_KEY)!!
    }

    private fun backPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(this.viewLifecycleOwner, object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        })
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.GAME_FRAGMENT_TAG, POP_BACK_STACK_INCLUSIVE)
    }

    companion object{
        private const val GAME_RESULT_KEY = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }
    }
}
