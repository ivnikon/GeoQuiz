package com.ivnikon.trainng.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nivanov on 09.11.2015.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE="com.ivnikon.trainng.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN="com.ivnikon.trainng.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;
    private boolean mCheater;
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mCheater = false;

        //setResult(RESULT_CANCELED);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);
        mShowAnswerButton=(Button)findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                mCheater=true;

            }
        });
       //if(savedInstanceState!=null) mCheater=savedInstanceState.getBoolean("cheater",false);
        if(mCheater)setResult(RESULT_OK);
        else setResult(RESULT_CANCELED);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putBoolean("cheater",mCheater);
    }
}
