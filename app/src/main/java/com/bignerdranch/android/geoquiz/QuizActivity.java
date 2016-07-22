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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        wireWidgets();
        setListeners();
    }

    // get references to
    private void wireWidgets() {
        mQuestionView = (TextView) findViewById(R.id.questionTextView);
        mTrueButton   = (Button)   findViewById(R.id.trueButton);
        mFalseButton  = (Button)   findViewById(R.id.falseButton);
    }

    private void setListeners() {
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });

        mFalseButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
