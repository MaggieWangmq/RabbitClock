package com.example.rabbitclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;

public final class FeedPage extends AppCompatActivity {
    String TAG = "Final_Project";

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedpage);
        ImageView sadRabbit = findViewById(R.id.sadRabbit);
        final ImageButton carrot = findViewById(R.id.carrotButton);
        carrot.setOnClickListener(v -> {
            Log.d(TAG, "Carrot button clicked");
            sadRabbit.setVisibility(View.INVISIBLE);
        });
        final Button back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FeedPage.this, MainPage.class));
            }
        });
    }
}