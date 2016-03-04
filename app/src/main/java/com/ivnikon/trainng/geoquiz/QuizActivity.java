package com.ivnikon.trainng.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX="index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;

    private TextView mQuestionTextView;
    private TextView mVersion;
    private TrueFalse[] mQuestionBank=new TrueFalse[]{
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_america,true),
            new TrueFalse(R.string.question_asia,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_ocean,true)
    };
    private int mCurrentIndex=0;
    private boolean isCheater;
    private void updateQuestion(){
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
        return;
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueFalse();
        int messageResId;
        if(isCheater)messageResId=R.string.judgement_toast;
        else
        {if(userPressedTrue==answerIsTrue)
            messageResId=R.string.correct_toast;
        else messageResId=R.string.incorrect_toast;}
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVersion=(TextView)findViewById(R.id.version);
        mVersion.append(Build.VERSION.SDK);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueFalse();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i, 0);
            }
        });
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=mCurrentIndex-1;
                if(mCurrentIndex<0)mCurrentIndex=mQuestionBank.length-1;
                isCheater=false;
                updateQuestion();
            }
        });
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                isCheater=false;
                updateQuestion();
            }
        });
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            isCheater=savedInstanceState.getBoolean("cheater", false);
            }
        updateQuestion();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)isCheater=true;
        else isCheater=false;
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean("cheater", isCheater);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
