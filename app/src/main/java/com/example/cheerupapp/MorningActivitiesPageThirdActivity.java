package com.example.cheerupapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cheerupapp.Constants.REQUEST_CODE_DANCE_PAGE_FOURTH_SCREEN;
import static com.example.cheerupapp.Constants.REQUEST_CODE_WELCOME_PAGE_SECOND_SCREEN;

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
                startActivity(goToDancePageIntent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DANCE_PAGE_FOURTH_SCREEN){
            if (resultCode == RESULT_OK){
                // if all good
                String messagePassedBackToLeavingUser = data.getStringExtra("USER_DANCE_LEAVE_MESSAGE");
                Toast.makeText(this, messagePassedBackToLeavingUser, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
