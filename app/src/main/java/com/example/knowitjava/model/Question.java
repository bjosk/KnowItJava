package com.example.knowitjava.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int answerIndex;
    private boolean isAnsweredWrong = false;
    private boolean isAnswered = false;
    public Question() {

    }

    public Question(String questionText,  String[] options, int answerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnsweredWrong() {
        return isAnsweredWrong;
    }

    public void setIsAnsweredWrong(boolean answeredWrong) {
        this.isAnsweredWrong = answeredWrong;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }
}
