package com.example.knowitjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowitjava.model.Question;
import com.example.knowitjava.model.Quiz;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class QuizResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        LinearLayout wrongQuestionsHolder = findViewById(R.id.wrongQuestionsHolder);
        TextView score = findViewById(R.id.score);
//        LinearLayout wrongQuestion = findViewById(R.id.wrongQuestion);
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        score.setText(quiz.getTotalCorrectAnswers() + "/" + quiz.getTotalQuestions());
//        TextView text = findViewById(R.id.result);
//        text.setText("Total correct answers: " + quiz.getTotalCorrectAnswers() + "/" + quiz.getTotalQuestions());
        List<Question> wrongAnswers = quiz.getQuestionsAnsweredWrong();

        Log.d("QuizResultActivity", "Number of wrong answers: " + wrongAnswers.size());
        for (Question question : wrongAnswers) {
            Log.d("QuizResultActivity", "Question: " + question.getQuestionText());
        }

        for (Question question : wrongAnswers) {
            TextView questionsText = new TextView(this);
            questionsText.setText(question.getQuestionText() + " Correct answer is: " + question.getOptions()[question.getAnswerIndex()]);
            questionsText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

//            TextView answerText = new TextView(this);
//            answerText.setText(question.getOptions()[question.getAnswerIndex()]);
//            answerText.setLayoutParams(new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));


            wrongQuestionsHolder.addView(questionsText);

        }
    }
}

