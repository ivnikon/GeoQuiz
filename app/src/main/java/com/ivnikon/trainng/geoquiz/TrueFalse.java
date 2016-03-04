package com.ivnikon.trainng.geoquiz;

/**
 * Created by nivanov on 09.11.2015.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueFalse;
    public TrueFalse(int question,boolean trueFalse){
        mQuestion=question;
        mTrueFalse=trueFalse;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueFalse() {
        return mTrueFalse;
    }

    public void setTrueFalse(boolean trueFalse) {
        mTrueFalse = trueFalse;
    }
}
