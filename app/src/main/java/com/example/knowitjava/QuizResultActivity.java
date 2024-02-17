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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        LinearLayout wrongQuestionsHolder = findViewById(R.id.wrongQuestionsHolder);
        MaterialCardView resultCard = findViewById(R.id.resultCard);
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView score = findViewById(R.id.score);
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
        this.quiz = quiz;
        saveHighScore(quiz.getTotalCorrectAnswers());

        score.setText(quiz.getTotalCorrectAnswers() + "/" + quiz.getTotalQuestions());

        List<Question> wrongAnswers = quiz.getQuestionsAnsweredWrong();

        if (!wrongAnswers.isEmpty()){

            for (Question question : wrongAnswers) {
                MaterialCardView questionCard = new MaterialCardView(this);
                questionCard.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                wrongQuestionsHolder.addView(questionCard);

                LinearLayout cardContent = new LinearLayout(this);
                questionCard.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                cardContent.setOrientation(LinearLayout.VERTICAL);
                cardContent.setPadding(32,32,32,32);

                questionCard.addView(cardContent);

                TextView questionText = new TextView(this);
                questionText.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                questionText.setText(question.getQuestionText());
                questionText.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_TitleMedium);
                questionText.setTextAlignment(TEXT_ALIGNMENT_CENTER);

                cardContent.addView(questionText);

                TextView correctAnswer = new TextView(this);
                correctAnswer.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                correctAnswer.setText("Correct answer: \n" + question.getOptions()[question.getAnswerIndex()]);
                correctAnswer.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                correctAnswer.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodyMedium);

                cardContent.addView(correctAnswer);
            }
        } else {
            resultCard.removeAllViews();
        }


    }

    // Checks if the score is higher than the current high score and updates the persisted value if it is higher.
    private void saveHighScore(int finalScore) {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        int highScore = prefs.getInt("highScore_" + quiz.getName() , 0);

        if (finalScore > highScore) {
            SharedPreferences.Editor editor = prefs.edit();
            Log.d("QuizResultActivity", "Saving new high score: " + finalScore);
            editor.putInt("highScore_" + quiz.getName(), finalScore);
            editor.apply(); // Saves the high score
        }
    }
}

