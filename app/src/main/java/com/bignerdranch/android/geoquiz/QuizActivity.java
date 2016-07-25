package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestionView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private int mCurrentIndex;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
        new Question(R.string.question_mideast, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        wireWidgets();
        setListeners();

        updateQuestion();
    }

    // get references to view objects.
    private void wireWidgets() {
        mQuestionView = (TextView) findViewById(R.id.questionTextView);
        mTrueButton   = (Button)   findViewById(R.id.trueButton);
        mFalseButton  = (Button)   findViewById(R.id.falseButton);
        mNextButton   = (Button)   findViewById(R.id.nextButton);
        mCurrentIndex = 0;

    }

    // set listeners to view objects.
    private void setListeners() {
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(false);
            }
        }));

        mNextButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        }));
    }

    // update the view with the latest question.
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionView.setText(question);
    }

    // check the answer to make sure it's correct. Provide appropriate feedback.
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
