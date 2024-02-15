package com.example.knowitjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowitjava.model.Question;
import com.example.knowitjava.model.Quiz;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

public class QuizSessionActivity extends AppCompatActivity {

    private Quiz quiz;
    private TextView questionTextView;
    private MaterialCardView questionsContainer;
    private LinearLayout optionsContainer;
    private MaterialButton skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_session);

        quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        questionTextView = findViewById(R.id.questionTextView);
        questionsContainer = findViewById(R.id.questionsContainer);
        optionsContainer = findViewById(R.id.optionsContainer);
        skipButton = findViewById(R.id.skipButton);

        skipButton.setOnClickListener(v -> skipQuestion());

        displayNextQuestion();
    }

    // Skips the current question without it being registered as answered wrong.
    // Does not award any points and will not be displayed in the result activity.
    private void skipQuestion() {
        displayNextQuestion();
    }

    // Loads the next question or sends the user to the result activity if the quiz is completed.
    private void displayNextQuestion() {
        Question currentQuestion = quiz.getNextQuestion();

        if (currentQuestion != null) {
            updateQuestionAndOptions(currentQuestion);
        } else if (quiz.isQuizComplete()) {
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();
//              THE CODE BELOW EITHER CRASHES THE APP OR RESTARTS THE FIRST ACTIVITY
            Intent intent = new Intent(QuizSessionActivity.this, QuizResultActivity.class);
            intent.putExtra("quiz", quiz); // Assuming Quiz implements Serializable
            startActivity(intent);
            finish();
        }
    }

    // Displays the next question and creates buttons for the question's options and removes previous options, if any.
    // If any of the option buttons are pressed the user will be prompted with a new question and options.
    // Notifies the user whether the question was answered correct or wrong.
    private void updateQuestionAndOptions(Question currentQuestion){
        questionTextView.setText(currentQuestion.getQuestionText());
        optionsContainer.removeAllViews();

        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            String option = currentQuestion.getOptions()[i];
            MaterialButton optionButton = new MaterialButton(this);
            optionButton.setText(option);
            optionButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            final int index = i;
            optionButton.setOnClickListener(v -> {
                if (quiz.verifyAnswer(currentQuestion, index)) {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Incorrect.", Toast.LENGTH_SHORT).show();
                }
                displayNextQuestion();
            });
            optionsContainer.addView(optionButton);
        }
    }
}
