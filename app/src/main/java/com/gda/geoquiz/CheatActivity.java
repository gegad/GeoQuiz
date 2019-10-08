package com.gda.geoquiz;

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
    private boolean mAnswerShown;

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
        mAnswerShown = false;

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

                Intent intent = new Intent();
                intent.putExtra(EXTRA_ANSWER_IS_SHOWN, true);
                setResult(RESULT_OK, intent);
            }
        });
    }
}
