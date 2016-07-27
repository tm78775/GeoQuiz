package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_CHEATER = "cheater_bool";
    private static final int REQUEST_CODE_CHEAT = 2424;
    private TextView mQuestionView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private int mCurrentIndex;
    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_tim, true)
    };



/*
 *  Override method calls.
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate called");
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
        }
        else {
            mCurrentIndex = 0;
        }

        wireWidgets();
        setListeners();
        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHEAT) {
                if (data == null) {
                    return;
                } else {
                    mIsCheater = CheatActivity.wasAnswerShown(data);
                    mQuestionBank[mCurrentIndex].setDidCheat(true);
                }
            }
        }
    }
/*
 *   Private methods.
 */

    // get references to view objects.
    private void wireWidgets() {
        mQuestionView = (TextView) findViewById(R.id.questionTextView);
        mTrueButton   = (Button) findViewById(R.id.trueButton);
        mFalseButton  = (Button) findViewById(R.id.falseButton);
        mNextButton   = (ImageButton) findViewById(R.id.nextButton);
        mPrevButton   = (ImageButton) findViewById(R.id.prevButton);
        mCheatButton  = (Button) findViewById(R.id.cheatButton);
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
                mIsCheater = false;
            }
        }));

        mQuestionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                updateQuestion();
                mIsCheater = false;
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
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
        if (mQuestionBank[mCurrentIndex].isDidCheat()) {
            messageResId = R.string.cheater_msg;
        }
        else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
