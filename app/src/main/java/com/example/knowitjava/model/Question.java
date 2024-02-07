package com.example.knowitjava.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int answerIndex;
    public Question() {

    }

    public Question(String questionText,  String[] options, int answerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.answerIndex = answerIndex;
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
