package com.example.knowitjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowitjava.model.Question;
import com.example.knowitjava.model.Quiz;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class QuizSessionActivity extends AppCompatActivity {

    private Quiz quiz;
    private TextView questionTextView;
    private MaterialCardView questionsContainer;
    private LinearLayout optionsContainer;
    private MaterialButton skipOrNextOrCompleteButton;
    private LinearProgressIndicator progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_session);

        quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        questionTextView = findViewById(R.id.questionTextView);
        questionsContainer = findViewById(R.id.questionsContainer);
        optionsContainer = findViewById(R.id.optionsContainer);
        skipOrNextOrCompleteButton = findViewById(R.id.skipButton);
        progressIndicator = findViewById(R.id.progressIndicator);

        skipOrNextOrCompleteButton.setOnClickListener(v -> displayNextQuestion());

        displayNextQuestion();
    }

    // Skips the current question without it being registered as answered wrong.
    // Does not award any points and will not be displayed in the result activity.



    // Loads the next question or sends the user to the result activity if the quiz is completed.
    private void displayNextQuestion() {

        updateProgressIndicator();

        Question currentQuestion = quiz.getNextQuestion();

        if (currentQuestion != null && quiz.getQuestionsQueue().isEmpty()) {
            skipOrNextOrCompleteButton.setText("Skip and complete");
            updateQuestionAndOptions(currentQuestion);
        } else if(currentQuestion != null) {
            skipOrNextOrCompleteButton.setText("skip");
            updateQuestionAndOptions(currentQuestion);
        } else if (quiz.isQuizComplete()) {
//            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();

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
//                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                    v.setBackgroundColor(getColor(R.color.success));
                } else {
//                    Toast.makeText(this, "Incorrect.", Toast.LENGTH_SHORT).show();
                    v.setBackgroundColor(getColor(R.color.error));
                }

                if (quiz.isQuizComplete()) {
                    skipOrNextOrCompleteButton.setText("Complete");
                } else {
                    skipOrNextOrCompleteButton.setText("Next question");
                }

                //Disable the buttons after an options has been pressed.
                for (int j = 0; j < optionsContainer.getChildCount(); j++) {
                    View child = optionsContainer.getChildAt(j);
                    child.setEnabled(false);
                }
//                displayNextQuestion();
            });
            optionsContainer.addView(optionButton);
        }
    }

    //Updates the progress indicator. Displays how far the user has gotten in the quiz.
    // Incorrect answers will not affect this as these questions are put back in the question queue.
    private void updateProgressIndicator() {
        int totalQuestions = quiz.getTotalQuestions();
        int questionsLeft = quiz.getQuestionsQueue().size();
        int questionsAnswered = totalQuestions - questionsLeft;

        // Calculate progress as a percentage
        float progress = ((float) (totalQuestions - questionsLeft) / totalQuestions) * 100;
        progressIndicator.setProgress((int) progress);
    }
}
