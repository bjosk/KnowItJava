package com.example.knowitjava.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {

    Question question;

    @Before
    public void setUp() {
        question = new Question("What is a variable in programming?", new String[]{"A storage location with a fixed value", "A type of function", "A storage location that can be changed", "An operation in math"}, 2);
    }

    @Test
    public void isAnswered_notAnswered() {
        assertEquals(false, question.isAnswered());
    }

    @Test
    public void isAnswered_answered() {
        question.setAnswered(true);
        assertEquals(true, question.isAnswered());
    }

    @Test
    public void isAnsweredWrong_no() {
        assertEquals(false, question.isAnsweredWrong());
    }

    @Test
    public void isAnsweredWrong_yes() {
        question.setIsAnsweredWrong(true);
        assertEquals(true, question.isAnsweredWrong());
    }

    @Test
    public void getQuestionText() {
        assertEquals("What is a variable in programming?", question.getQuestionText());
    }
}