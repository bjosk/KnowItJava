package com.example.knowitjava.model;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizData {

    public static ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        Quiz infrastructureQuiz = new Quiz("IT infrastructure", new ArrayList<>(Arrays.asList(
                new Question("What is the capital of Sweden?", new String[]{"Oslo", "Stockholm", "Copenhagen", "Helsinki"}, 1),
                new Question("What is the capital of Germany?", new String[]{"Berlin", "Amsterdam", "Rotterdam", "Antwerpen"}, 0)
        )));

        Quiz softwareDevQuiz = new Quiz("Software Development Fundamentals", new ArrayList<>(Arrays.asList(
                new Question("Which language runs in a web browser?", new String[]{"Java", "C#", "Python", "JavaScript"}, 3),
                new Question("What does 'MVC' stand for?", new String[]{"Model View Controller", "Multiple View Control", "Model View Control", "Main Virtual Control"}, 0)
        )));

        quizzes.add(infrastructureQuiz);
        quizzes.add(softwareDevQuiz);

        return quizzes;
    }
}
