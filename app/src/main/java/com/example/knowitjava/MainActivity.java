package com.example.knowitjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.knowitjava.model.Quiz;
import com.example.knowitjava.model.QuizData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout quizContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizContainer = findViewById(R.id.quizContainer);
        setupQuizzesAndScores();
//        resetAllHighScores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Updates the view with new high scores
        setupQuizzesAndScores();
    }

    // Gets all the available quizzes and its high scores and displays them
    private void setupQuizzesAndScores() {
        // Clear existing views to avoid duplication
        quizContainer.removeAllViews();

        ArrayList<Quiz> quizzes = QuizData.getQuizzes();

        for (Quiz quiz : quizzes) {
            MaterialButton quizButton = createQuizButton(quiz);
            TextView highScoreTextView = createHighScoreTextView(quiz);

            quizContainer.addView(quizButton);
            quizContainer.addView(highScoreTextView);
        }
    }

    // Creates a button for a quiz and loads the quiz into the next activity if pressed
    private MaterialButton createQuizButton(Quiz quiz) {
        MaterialButton button = new MaterialButton(this);

        button.setText(quiz.getName());
        button.setCornerRadius(16);

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

    // Creates a TextView which displays the high score for a quiz
    private TextView createHighScoreTextView(Quiz quiz) {
        TextView highScore = new TextView(this);
        highScore.setText("High Score: " + getHighScore(quiz)); // Modified to include "High Score: "
        highScore.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return highScore;
    }

    // Retrieves the persisted high score value for a quiz.
    private int getHighScore(Quiz quiz) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        return prefs.getInt("highScore_" + quiz.getName(), 0);
    }

    // Sets the high score values to 0
    private void resetAllHighScores() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear();

        editor.apply();
    }
}