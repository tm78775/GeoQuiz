package com.bignerdranch.android.geoquiz;

/**
 * Created by TMiller on 7/22/2016.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

}
