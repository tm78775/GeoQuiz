package com.bignerdranch.android.geoquiz;

/**
 * Created by TMiller on 7/22/2016.
 */
public class Question {

    // Member variables.
    private int mTextResId;
    private boolean mAnswerTrue;

    // Constructor
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    // Getters/Setters for mTextResId.
    public int getTextResId() { return mTextResId; }
    public void setTextResId(int textResId) { mTextResId = textResId; }

    // Getters/Setters for mAnswerTrue.
    public boolean isAnswerTrue() { return mAnswerTrue; }
    public void setAnswerTrue(boolean answerTrue) { mAnswerTrue = answerTrue; }

}
