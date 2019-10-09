package com.gda.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.gda.geoquiz.ANSWER_IS_TRUE";
    private static final String EXTRA_ANSWER_IS_SHOWN = "com.gda.geoquiz.ANSWER_IS_SHOWN";
    private boolean mIsAnswerTrue;
    private static boolean mAnswerShown = false;
//    private Intent mResultIntent = null;

    public static Intent newIntent(Context context, boolean isAsnwerTrue) {

        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, isAsnwerTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mIsAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        Button answerButton = findViewById(R.id.showAnswerButton);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView answerTxt = findViewById(R.id.answerTextView);
                if (mIsAnswerTrue) {
                    answerTxt.setText(R.string.true_button);
                } else {
                    answerTxt.setText(R.string.false_button);
                }

                mAnswerShown = true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mResultIntent = new Intent();
        mResultIntent.putExtra(EXTRA_ANSWER_IS_SHOWN, mAnswerShown);
        setResult(RESULT_OK, mResultIntent);
        mAnswerShown = false;
        super.onBackPressed();
    }
}
