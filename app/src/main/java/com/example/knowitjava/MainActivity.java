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
import com.google.android.material.resources.TextAppearance;

import java.util.ArrayList;

// Displays a list of quizzes available for the user
public class MainActivity extends AppCompatActivity {

    private LinearLayout quizContainer; // Container for the dynamically created buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set up the user interface
        quizContainer = findViewById(R.id.quizContainer); // Initialize the container
        setupQuizzesAndScores(); // Set up and display the buttons and high scores
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

        //Creates a button for each quiz and adds it to the container
        for (Quiz quiz : quizzes) {
            MaterialButton quizButton = createQuizButton(quiz);

            quizContainer.addView(quizButton);
        }
    }

    /**
     * Creates a fully configured and functional button for a quiz.
     * @param quiz The quiz in which the button is created for
     * @return A fully configured MaterialButton
     */

    private MaterialButton createQuizButton(Quiz quiz) {
        MaterialButton button = new MaterialButton(this);
        button.setBackgroundColor(getColor(com.google.android.material.R.color.m3_sys_color_light_primary));

        button.setText(quiz.getName() + "\nBest: " + getHighScore(quiz) + "/" + quiz.getTotalQuestions());

        button.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_TitleMedium);
        button.setTextColor(getColor(com.google.android.material.R.color.m3_sys_color_light_on_primary));
        button.setLineSpacing(0f, 1.25f);

        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        // Loads the quiz into the next activity if pressed
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizSessionActivity.class);
            intent.putExtra("quiz", quiz);
            startActivity(intent);
        });
        return button;
    }

    /**
     * Retrieves the persisted high score value for a quiz.
     * @param quiz The quiz in which the high score is retrieved from
     * @return The high score as an integer
     */
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