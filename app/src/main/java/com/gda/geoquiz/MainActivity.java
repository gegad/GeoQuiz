package com.gda.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "GeoQuiz";
    private final static int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private boolean mIsCheater = false;

    private Question[] mQuestionsBank = new Question[] {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true),
    };
    private int mCurrentQuestionId = 0;
    private void updateQuestion() {
        Log.d(TAG, "updateQuestion --->", new Exception());
        int questionId = mQuestionsBank[mCurrentQuestionId].getTextResId();
        mQuestionTextView.setText(questionId);
    }

    public void checkAnswer(boolean userPressedTrue) {
        boolean correctAnswer = mQuestionsBank[mCurrentQuestionId].isAnswerTrue();
        int toastId = 0;
        if (mIsCheater != true) {
            if (userPressedTrue == correctAnswer) {
                toastId = R.string.correct_toast;
            } else {
                toastId = R.string.incorrect_toast;
            }
        } else {
            toastId = R.string.judgement_toast;
        }
        Toast.makeText(MainActivity.this,
                toastId,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text);
        updateQuestion();

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQuestionId++;
                if (mCurrentQuestionId == mQuestionsBank.length) {
                    mCurrentQuestionId = 0;
                }
                mIsCheater = false;
                updateQuestion();
            }
        });


        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bla bla bla True
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bla bla bla False
                checkAnswer(false);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheatButton);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO: Start CheatActivity
                boolean isAnswerTrue = mQuestionsBank[mCurrentQuestionId].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, isAnswerTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode != REQUEST_CODE_CHEAT) {
            return;
        }

        if (data == null) {
            return;
        }
        mIsCheater = CheatActivity.wasAnswerShown(data);
    }
}
