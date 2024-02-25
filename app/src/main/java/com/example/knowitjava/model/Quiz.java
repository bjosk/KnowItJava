package com.example.knowitjava.model;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Quiz implements Serializable {

    private String name;
    private List<Question> questionsOriginal;
    private Deque<Question> questionsQueue;
    private List<Question> questionsAnsweredWrong = new ArrayList<>();
    private int totalCorrectAnswers = 0;

    private int totalQuestions = 0;
    private int highScore = 0;

    public Quiz() {

    }

    public Quiz(String name, List<Question> questions) {
        this.name = name;
        this.questionsOriginal = questions;
        this.questionsQueue = new LinkedList<>(questions);
        this.totalQuestions = questionsOriginal.size();
    }

    public int getHighScore() {
        return highScore;
    }

    public List<Question> getQuestionsAnsweredWrong() {
        return questionsAnsweredWrong;
    }

    public void addQuestionsAnsweredWrong(Question question) {
        questionsAnsweredWrong.add(question);
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionsOriginal() {
        return questionsOriginal;
    }

    public void setQuestionsOriginal(List<Question> questionsOriginal) {
        this.questionsOriginal = questionsOriginal;
    }

    public Deque<Question> getQuestionsQueue() {
        return questionsQueue;
    }

    public void setQuestionsQueue(Deque<Question> questionsQueue) {
        this.questionsQueue = questionsQueue;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    public Question getNextQuestion() {
        return questionsQueue.poll();
    }


    /**
     * Reschedules a question
     * @param question Incorrectly answered question
     */
    public void retryQuestion(Question question) {
        if (!questionsQueue.contains(question)){
            questionsQueue.offer(question);
        }
    }

    /**
     * Checks if the quiz is completed
     * @return True if completed, false if there are questions left
     */
    public boolean isQuizComplete() {
        return questionsQueue.isEmpty();
    }

    /**
     * Verifies the answer of a question and updates the quiz state
     * @param question The answered question
     * @param answerIndex The index of the selected answer option
     * @return true or false indicating correct or incorrect answer
     */
    public boolean verifyAnswer(Question question, int answerIndex) {
        boolean isCorrect = question.getAnswerIndex() == answerIndex;

        if (isCorrect && !question.isAnsweredWrong()) {
            totalCorrectAnswers++; // Increases the score by one if answered correct
        } else if (!isCorrect && !question.isAnsweredWrong()) {
            // Reschedules incorrectly answered questions and adds them to a list of questions
            addQuestionsAnsweredWrong(question);
            question.setIsAnsweredWrong(true);
            retryQuestion(question);
        }
        else if (!isCorrect) {
            // If already answered wrong once the question will not be added to the list
            question.setIsAnsweredWrong(true);
            retryQuestion(question); // Reschedule the question if the answer is incorrect
        }
        return isCorrect;
    }
}