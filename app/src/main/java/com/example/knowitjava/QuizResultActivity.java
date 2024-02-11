package com.example.knowitjava;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private Quiz quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        LinearLayout wrongQuestionsHolder = findViewById(R.id.wrongQuestionsHolder);
        TextView score = findViewById(R.id.score);
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
        this.quiz = quiz;
        saveHighScore(quiz.getTotalCorrectAnswers());

        score.setText(quiz.getTotalCorrectAnswers() + "/" + quiz.getTotalQuestions());

        List<Question> wrongAnswers = quiz.getQuestionsAnsweredWrong();

        for (Question question : wrongAnswers) {
            TextView questionsText = new TextView(this);
            questionsText.setText(question.getQuestionText() + " Correct answer is: " + question.getOptions()[question.getAnswerIndex()]);
            questionsText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            wrongQuestionsHolder.addView(questionsText);

        }
    }

    // Checks if the score is higher than the current high score and updates the persisted value if it is higher.
    private void saveHighScore(int finalScore) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        int highScore = prefs.getInt("highScore_" + quiz.getName() , 0);

        if (finalScore > highScore) {
            SharedPreferences.Editor editor = prefs.edit();
            Log.d("QuizResultActivity", "Saving new high score: " + finalScore);
            editor.putInt("highScore_" + quiz.getName(), finalScore);
            editor.apply(); // Saves the high score
        }
    }
}

