package com.example.knowitjava;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

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
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class QuizResultActivity extends AppCompatActivity {
    private Quiz quiz;
    private LinearLayout wrongQuestionsholder;
    private MaterialCardView resultCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        // Initialize UI views
        this.wrongQuestionsholder = findViewById(R.id.wrongQuestionsHolder);
        this.resultCard = findViewById(R.id.resultCard);
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView score = findViewById(R.id.score);

        // Retrieve the quiz from the quiz session
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
        this.quiz = quiz;
        // Saves the new high score if higher than the current high score
        saveHighScore(quiz.getTotalCorrectAnswers());

        score.setText(quiz.getTotalCorrectAnswers() + "/" + quiz.getTotalQuestions());

        List<Question> wrongAnswers = quiz.getQuestionsAnsweredWrong();

        // Displays a list of the incorrectly answered questions
        displayResult(wrongAnswers);
    }

    /**
     * Displays cards for each incorrectly answered question
     * @param wrongAnswers The list of incorrectly answered questions from the quiz
     */
    private void displayResult(List<Question> wrongAnswers) {
        // Checks if there are any wrong answers
        if (!wrongAnswers.isEmpty()){
            // Create a card for each incorrect question
            for (Question question : wrongAnswers) {
                MaterialCardView questionCard = new MaterialCardView(this);
                questionCard.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                // Adds the card to the layout
                this.wrongQuestionsholder.addView(questionCard);

                // Creates a layout for the contents of the card
                LinearLayout cardContent = new LinearLayout(this);
                questionCard.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                cardContent.setOrientation(LinearLayout.VERTICAL);
                cardContent.setPadding(32,32,32,32);

                // Adds the layout to the card
                questionCard.addView(cardContent);

                // Creates a text view for the content layout displaying the question
                TextView questionText = new TextView(this);
                questionText.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                questionText.setText(question.getQuestionText());
                questionText.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_TitleMedium);
                questionText.setTextAlignment(TEXT_ALIGNMENT_CENTER);

                // Adds the text view to the content layout
                cardContent.addView(questionText);

                // Creates a text view for the content layout displaying the correct answer
                TextView correctAnswer = new TextView(this);
                correctAnswer.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                correctAnswer.setText("Correct answer: \n" + question.getOptions()[question.getAnswerIndex()]);
                correctAnswer.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                correctAnswer.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodyMedium);

                // Adds the view to the content layout
                cardContent.addView(correctAnswer);
            }
            // If no incorrect answers the card is cleared leaving only the score
        } else {
            resultCard.removeAllViews();
        }
    }

    /**
     * Checks if the score is higher than the current high score and updates the persisted value if it is higher.
     * @param finalScore The score achieved from the quiz
     */
    private void saveHighScore(int finalScore) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        int highScore = prefs.getInt("highScore_" + quiz.getName() , 0);

        // Check if the current score is higher than the current high score
        if (finalScore > highScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore_" + quiz.getName(), finalScore);
            editor.apply(); // Saves the high score
        }
    }
}

