package com.example.rabbitclock;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/*
the class for MainPage.
 */
public final class MainPage extends AppCompatActivity {

    private EditText setTime;
    private Button setButton;

    private static long StartTimeInMillins;

    private CountDownTimer CountDownTimer;
    private TextView timeDisplay;
    private Button StartPause;
    private Button retry;
    private boolean TimerRunning;

    private long TimeLeft = StartTimeInMillins;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        setTime = findViewById(R.id.setTime);
        setButton = findViewById(R.id.setButton);
        ImageButton rabbitButton = findViewById(R.id.rabbitButton);
        rabbitButton.setVisibility(View.INVISIBLE);
        TextView Clickme = findViewById(R.id.hintClick);
        Clickme.setVisibility(View.INVISIBLE);
        timeDisplay = findViewById(R.id.timeDisplay);

        StartPause = findViewById(R.id.startButton);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = setTime.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(MainPage.this, "Time can't be zero!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(MainPage.this, "Time should be positive!", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                setTime.setText("");
                setButton.setVisibility(View.INVISIBLE);
            }
        });
        StartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                    rabbitButton.setVisibility(View.VISIBLE);
                    Clickme.setVisibility(View.VISIBLE);
                }
            }
        });

        retry = findViewById(R.id.retryButton);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                setButton.setVisibility(View.VISIBLE);
            }
        });
        updateCountDownText();

        final ImageButton rabbit = findViewById(R.id.rabbitButton);
        rabbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, FeedPage.class));
            }
        });
    }
    private void setTime(long millisec) {
        StartTimeInMillins = millisec;
        resetTimer();
    }
    private void startTimer() {
        CountDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                StartPause.setText("START");

            }
        }.start();
        TimerRunning = true;
        StartPause.setText("PAUSE");
    }
    private void pauseTimer() {
        CountDownTimer.cancel();
        TimerRunning = false;
        StartPause.setText("START");
    }
    private void resetTimer() {
        TimeLeft = StartTimeInMillins;
        updateCountDownText();
    }
    private void updateCountDownText() {
        int hur = (int) (TimeLeft / 1000) / 60 / 60 % 60;
        int min = (int) (TimeLeft / 1000) / 60 % 60;
        int sec = (int) (TimeLeft / 1000) % 60;

        String TimeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hur, min, sec);

        timeDisplay.setText(TimeLeftFormatted);
    }
}
