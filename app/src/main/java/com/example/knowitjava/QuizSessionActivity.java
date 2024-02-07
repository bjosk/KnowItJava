package com.example.knowitjava;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowitjava.model.Question;
import com.example.knowitjava.model.Quiz;

import org.w3c.dom.Text;

public class QuizSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_session);

        LinearLayout questionsContainer = findViewById(R.id.questionsContainer);
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");

//            TextView testText = findViewById(R.id.testText);
//            testText.setText(quiz.getName());

        for (Question question : quiz.getQuestions()){
            TextView questionTextView = new TextView(this);
            questionTextView.setText(question.getQuestionText());
            questionTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            questionTextView.setPadding(16, 16, 16, 16);

            questionsContainer.addView(questionTextView);
        }

        // Use the quiz object to update UI components in this activity
    }
}