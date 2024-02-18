package com.example.knowitjava.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class QuizTest {

    private Quiz quiz;

    @Before
    public void setUp(){
        quiz = new Quiz("Basic Programming Concepts", new LinkedList<>(Arrays.asList(
                new Question("What is a variable in programming?", new String[]{"A storage location with a fixed value", "A type of function", "A storage location that can be changed", "An operation in math"}, 2),
                new Question("Which of the following is a loop structure in programming?", new String[]{"If-Else", "Switch-Case", "For", "Try-Catch"}, 2),
                new Question("What does 'IDE' stand for in software development?", new String[]{"Integrated Development Environment", "Internal Device Emulator", "Intelligent Design Engine", "Integrated Device Engineering"}, 0),
                new Question("Which data type is typically used to store a boolean value?", new String[]{"int", "boolean", "string", "char"}, 1),
                new Question("What is encapsulation in Object-Oriented Programming?", new String[]{"The process of creating threads", "The inclusion of data and methods within a class", "The conversion of one type of object into another", "The process of breaking a program into modules"}, 1),
                new Question("Which keyword is used to create a new instance of an object in Java?", new String[]{"struct", "class", "new", "object"}, 2),
                new Question("What is recursion in programming?", new String[]{"A syntax error", "A function that calls itself", "A loop that never ends", "A method of iterating over an array"}, 1),
                new Question("Which of the following is NOT a primitive data type in Java?", new String[]{"String", "int", "boolean", "double"}, 0),
                new Question("What does 'API' stand for?", new String[]{"Application Programming Interface", "Application Protocol Integration", "Advanced Programming Interface", "Automated Programming Instructions"}, 0),
                new Question("What is the purpose of the 'git pull' command?", new String[]{"To send local commits to a remote repository", "To delete a branch", "To merge a remote branch into the current branch", "To copy a repository into a new directory"}, 2)
        )));
    }

    @Test
    public void testGetNextQuestion_ReturnsQuestion() {
        Question question = quiz.getNextQuestion();

        assertEquals("What is a variable in programming?", question.getQuestionText());
    }
//    @Test
//    public void getNextQuestion() {
//        assert
//    }

    @Test
    public void retryQuestion_AddsQuestionToQueueAgain() {
        Question question = quiz.getNextQuestion();
        quiz.retryQuestion(question);
        assertEquals(question.getQuestionText(), quiz.getQuestionsQueue().getLast().getQuestionText());
    }

    @Test
    public void isQuizComplete() {
    }

    @Test
    public void verifyAnswer() {
    }
}