package com.example.knowitjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.knowitjava.model.Question;
import com.example.knowitjava.model.Quiz;
import com.example.knowitjava.model.QuizData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout quizContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizContainer = findViewById(R.id.quizContainer);
        setupQuizzesAndScores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupQuizzesAndScores(); // Call this again to refresh scores
    }

    private void setupQuizzesAndScores() {
        quizContainer.removeAllViews(); // Clear existing views to avoid duplication
        ArrayList<Quiz> quizzes = QuizData.getQuizzes();

        for (Quiz quiz : quizzes) {
            MaterialButton quizButton = createQuizButton(quiz);
            TextView highScoreTextView = createHighScoreTextView(quiz);

            quizContainer.addView(quizButton);
            quizContainer.addView(highScoreTextView);
        }
    }

    private MaterialButton createQuizButton(Quiz quiz) {
        MaterialButton button = new MaterialButton(this);
        button.setText(quiz.getName());
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizSessionActivity.class);
            intent.putExtra("quiz", quiz); // Assuming Quiz implements Serializable
            startActivity(intent);
        });
        return button;
    }

    private TextView createHighScoreTextView(Quiz quiz) {
        TextView highScore = new TextView(this);
        highScore.setText("High Score: " + displayHighScore(quiz)); // Modified to include "High Score: "
        highScore.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return highScore;
    }

    private int displayHighScore(Quiz quiz) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        return prefs.getInt("highScore_" + quiz.getName(), 0);
    }

    private void resetAllHighScores() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear();

        editor.apply();
    }
}