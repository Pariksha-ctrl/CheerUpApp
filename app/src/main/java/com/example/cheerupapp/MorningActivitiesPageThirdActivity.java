package com.example.cheerupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MorningActivitiesPageThirdActivity extends AppCompatActivity {

    TextView userGreetingTextView;
    Button danceButton;
    Button goBackToWelcomePageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_activities_page_third);
/*        final User user;

        Intent userCallerIntent = new Intent();
        user = (User) userCallerIntent.getSerializableExtra(User.USER_KEY);

        userNameTextView = findViewById(R.id.userNameTextView);
        userNameTextView.setText(user.getName());*/

        userGreetingTextView = findViewById(R.id.userGreetingTextView);
        userGreetingTextView.setText("Hi There!");


/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        danceButton = findViewById(R.id.danceButton);

        danceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDancePageIntent = new Intent(MorningActivitiesPageThirdActivity.this, DancePageFifthActivity.class);
            }
        });

        goBackToWelcomePageButton = findViewById(R.id.goBackToWelcomePageButton);

        goBackToWelcomePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goingBackToWelcomePageIntent = new Intent();
                //goingBackToWelcomePageIntent.putExtra(User.USER_KEY, user);
                setResult(RESULT_OK, goingBackToWelcomePageIntent);
                finish();
            }
        });
    }
}
