package com.example.myapplication.ui.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentQuizBinding;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;
    private TextView txtQuestion;
    private Button btnF;
    private Button btnT;
    private int questionIndex = 0;
    private int quizQuestion;
    private ProgressBar progressBar;
    private TextView quizStatsTextView;
    private int userScore;

    private final QuizViewModel[] questionCollection = new QuizViewModel[]{
            new QuizViewModel(R.string.q1, true),
            new QuizViewModel(R.string.q2, true),
            new QuizViewModel(R.string.q3, true),
            new QuizViewModel(R.string.q6, true),
            new QuizViewModel(R.string.q5, true),
            new QuizViewModel(R.string.q4, true),
    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);

        txtQuestion = binding.txtQuestion;
        QuizViewModel q1 = questionCollection[questionIndex];
        quizQuestion = q1.getQuestion();
        txtQuestion.setText(quizQuestion);
        progressBar = binding.quizPB;
        quizStatsTextView = binding.txtQuizStats;

        btnT = binding.btnTrue;
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnF = binding.btnFalse;
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void changeQuestionOnButtonClick() {
        questionIndex = (questionIndex + 1) % 6;
        if (questionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(getActivity());
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished");
            quizAlert.setMessage("Your Score is " + userScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                }
            });
            quizAlert.show();
        } else {
            quizQuestion = questionCollection[questionIndex].getQuestion();
            txtQuestion.setText(quizQuestion);
            progressBar.incrementProgressBy(USER_PROGRESS);
            quizStatsTextView.setText(userScore + "" + "/ 6");
        }
    }

    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = questionCollection[questionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess) {
            userScore++;
        }
    }

}