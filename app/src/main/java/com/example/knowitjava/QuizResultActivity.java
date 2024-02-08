package com.example.knowitjava;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowitjava.model.Quiz;

public

class QuizResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        TextView text = findViewById(R.id.result);
        text.setText("Total correct answers: " + quiz.getTotalCorrectAnswers());


    }
}

