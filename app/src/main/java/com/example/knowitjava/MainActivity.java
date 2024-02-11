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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout quizContainer = findViewById(R.id.quizContainer);
        ArrayList<Quiz> quizzes = QuizData.getQuizzes();

        for (Quiz quiz : quizzes) {
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

            TextView highScore = new TextView(this);
            highScore.setText(Integer.toString(displayHighScore(quiz)));
            highScore.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Add the button to the LinearLayout
            quizContainer.addView(button);
            quizContainer.addView(highScore);

        }
    }

    private int displayHighScore(Quiz quiz) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        int highScore = prefs.getInt("highScore_" + quiz.getName(), 0);

        return highScore;
    }
}