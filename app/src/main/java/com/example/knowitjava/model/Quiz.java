package com.example.knowitjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Quiz implements Serializable {

    private String name;
    private List<Question> questionsOriginal;
    private Queue<Question> questionsQueue;
    private List<Question> questionsAnsweredWrong = new ArrayList<>();
    private int totalCorrectAnswers = 0;

    private int totalQuestions = 0;

    public Quiz() {

    }

    public Quiz(String name, List<Question> questions) {
        this.name = name;
        this.questionsOriginal = questions;
        this.questionsQueue = new LinkedList<>(questions);
        this.totalQuestions = questionsOriginal.size();
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

    public Queue<Question> getQuestionsQueue() {
        return questionsQueue;
    }

    public void setQuestionsQueue(Queue<Question> questionsQueue) {
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

//    public void skipQuestion() {
//        questionsQueue.remove();
//    }

    public void retryQuestion(Question question) {
        if (!questionsQueue.contains(question)){
            questionsQueue.offer(question);
        }
    }

    public boolean isQuizComplete() {
        return questionsQueue.isEmpty();
    }

    public boolean verifyAnswer(Question question, int answerIndex) {
        boolean isCorrect = question.getAnswerIndex() == answerIndex;
        if (isCorrect && !question.isAnsweredWrong()) {
            totalCorrectAnswers++;
        } else if (!isCorrect && !question.isAnsweredWrong()) {
            addQuestionsAnsweredWrong(question);
            question.setIsAnsweredWrong(true);
            retryQuestion(question);
        }
        else if (!isCorrect) {
            question.setIsAnsweredWrong(true);
            retryQuestion(question); // Re-queue the question if the answer is incorrect
        }
        return isCorrect;
    }
}