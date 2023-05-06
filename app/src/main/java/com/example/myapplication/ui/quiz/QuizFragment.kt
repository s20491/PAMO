package com.example.myapplication.ui.quiz

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private var binding: FragmentQuizBinding? = null
    private var txtQuestion: TextView? = null
    private var btnF: Button? = null
    private var btnT: Button? = null
    private var questionIndex = 0
    private var quizQuestion = 0
    private var progressBar: ProgressBar? = null
    private var quizStatsTextView: TextView? = null
    private var userScore = 0
    private val questionCollection = arrayOf(
            QuizViewModel(R.string.q1, true),
            QuizViewModel(R.string.q2, true),
            QuizViewModel(R.string.q3, true),
            QuizViewModel(R.string.q6, true),
            QuizViewModel(R.string.q5, true),
            QuizViewModel(R.string.q4, true))
    val USER_PROGRESS = Math.ceil(100.0 / questionCollection.size).toInt()
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        txtQuestion = binding!!.txtQuestion
        val q1 = questionCollection[questionIndex]
        quizQuestion = q1.question
        txtQuestion!!.setText(quizQuestion)
        progressBar = binding!!.quizPB
        quizStatsTextView = binding!!.txtQuizStats
        btnT = binding!!.btnTrue
        btnT!!.setOnClickListener {
            evaluateUserAnswer(true)
            changeQuestionOnButtonClick()
        }
        btnF = binding!!.btnFalse
        btnF!!.setOnClickListener {
            evaluateUserAnswer(false)
            changeQuestionOnButtonClick()
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun changeQuestionOnButtonClick() {
        questionIndex = (questionIndex + 1) % 6
        if (questionIndex == 0) {
            val quizAlert = AlertDialog.Builder(activity)
            quizAlert.setCancelable(false)
            quizAlert.setTitle("Quiz is Finished")
            quizAlert.setMessage("Your Score is $userScore")
            quizAlert.setPositiveButton("Finish the Quiz") { dialogInterface, i -> requireActivity().finish() }
            quizAlert.show()
        } else {
            quizQuestion = questionCollection[questionIndex].question
            txtQuestion!!.setText(quizQuestion)
            progressBar!!.incrementProgressBy(USER_PROGRESS)
            quizStatsTextView!!.text = "$userScore/ 6"
        }
    }

    private fun evaluateUserAnswer(userGuess: Boolean) {
        val currentQuestionAnswer = questionCollection[questionIndex].isAnswer
        if (currentQuestionAnswer == userGuess) {
            userScore++
        }
    }
}