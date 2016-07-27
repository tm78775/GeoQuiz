package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String KEY_DID_CHEAT = "com.bignerdranch.android.geoquiz.did_cheat";
    private static final String TAG = "QuizActivity";
    private boolean mDidCheat;
    private TextView answerView;
    private TextView buildView;
    private Button showButton;
    private boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        if (savedInstanceState != null) {
            mDidCheat = savedInstanceState.getBoolean(KEY_DID_CHEAT, false);
        }

        wireLayout();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setAnswerShownResult(mDidCheat);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState called");
        savedInstanceState.putBoolean(KEY_DID_CHEAT, mDidCheat);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed called");
        setAnswerShownResult(mDidCheat);
        super.onBackPressed();
    }

    private void wireLayout() {
        answerView = (TextView) findViewById(R.id.answerTextView);
        showButton = (Button) findViewById(R.id.showAnswerButton);
        buildView  = (TextView) findViewById(R.id.buildTextView);

        int b = Build.VERSION.SDK_INT;
        buildView.setText("Android Version: " + b);

        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDidCheat = true;
                if (mAnswerIsTrue) { answerView.setText(R.string.trueText); }
                else { answerView.setText(R.string.falseText); }
                setAnswerShownResult(true);
            }
        });
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    private void setAnswerShownResult(boolean answerShown) {
        Intent data = new Intent();
        if (mDidCheat) { data.putExtra(EXTRA_ANSWER_SHOWN, mDidCheat); }
        else { data.putExtra(EXTRA_ANSWER_SHOWN, answerShown); }
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

}
