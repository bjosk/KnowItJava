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

        //Retrieve the quiz object passed from the main activity
        quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        // Initialization of views
        questionTextView = findViewById(R.id.questionTextView);
        questionsContainer = findViewById(R.id.questionsContainer);
        optionsContainer = findViewById(R.id.optionsContainer);
        skipOrNextOrCompleteButton = findViewById(R.id.skipButton);
        progressIndicator = findViewById(R.id.progressIndicator);

        skipOrNextOrCompleteButton.setOnClickListener(v -> displayNextQuestion());

        // Display the first question
        displayNextQuestion();
    }

    /**
     * Loads the next question or sends the user to the result activity if the quiz is completed.
     */
    private void displayNextQuestion() {

        updateProgressIndicator();

        Question currentQuestion = quiz.getNextQuestion();

        // Check if there is a next question and update the button text accordingly
        if (currentQuestion != null && quiz.getQuestionsQueue().isEmpty()) {
            skipOrNextOrCompleteButton.setText("Skip and complete");
            updateQuestionAndOptions(currentQuestion);
        } else if(currentQuestion != null) {
            skipOrNextOrCompleteButton.setText("skip");
            updateQuestionAndOptions(currentQuestion);
        } else if (quiz.isQuizComplete()) {
            // If there are no questions left the app navigates to the result screen
            Intent intent = new Intent(QuizSessionActivity.this, QuizResultActivity.class);
            intent.putExtra("quiz", quiz);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Updates the user interface with the current question and its answer options.
     * @param currentQuestion
     */
    private void updateQuestionAndOptions(Question currentQuestion){
        questionTextView.setText(currentQuestion.getQuestionText());
        optionsContainer.removeAllViews();

        // Dynamically create and add buttons for each answer option
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            String option = currentQuestion.getOptions()[i];
            MaterialButton optionButton = new MaterialButton(this);
            optionButton.setText(option);
            optionButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            final int index = i;
            // Check if the answer was correct and update the UI accordingly
            optionButton.setOnClickListener(v -> {
                if (quiz.verifyAnswer(currentQuestion, index)) {
                    v.setBackgroundColor(getColor(R.color.success));
                } else {
                    v.setBackgroundColor(getColor(R.color.error));
                }

                // Update the button text based on the status of the quiz
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
            });
            optionsContainer.addView(optionButton);
        }
    }

    /**
     * Updates the progress indicator. Displays how many questions have been answered.
     */
    private void updateProgressIndicator() {
        int totalQuestions = quiz.getTotalQuestions();
        int questionsLeft = quiz.getQuestionsQueue().size();
        int questionsAnswered = totalQuestions - questionsLeft;

        // Calculate progress as a percentage
        float progress = ((float) (totalQuestions - questionsLeft) / totalQuestions) * 100;
        progressIndicator.setProgress((int) progress);
    }
}
