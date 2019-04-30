package com.example.rabbitclock;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/*
the class for startPage.
 */
public final class StartPage extends AppCompatActivity {

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);

        final Button enter = findViewById(R.id.enterButton);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this, MainPage.class));
            }
        });
    }
}