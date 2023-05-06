package com.example.myapplication.ui.quiz;

import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    private int mQuestion;
    private boolean mAnswer;

    public QuizViewModel(int mQuestion, boolean mAnswer) {
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}