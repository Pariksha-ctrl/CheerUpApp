package com.example.cheerupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePageSecondActivity extends AppCompatActivity {

    Button goBackToMainPageButton;
    Button letsDoCheerfulActivitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_second);

        final User user;

        Intent userCallerIntent = getIntent();
        user = (User) userCallerIntent.getSerializableExtra(User.USER_KEY);

        TextView goodMorningUserNameTextView = findViewById(R.id.goodMorningUserNameTextView);
        goodMorningUserNameTextView.setText(user.getName());

        letsDoCheerfulActivitiesButton = findViewById(R.id.letsDoCheerfulActivitiesButton);
        goBackToMainPageButton = findViewById(R.id.goBackToMainPageButton);

        goBackToMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goingBackToMainPageIntent = new Intent();
                goingBackToMainPageIntent.putExtra(User.USER_KEY, user);
                goingBackToMainPageIntent.putExtra("USER_LEAVING_MESSAGE", "See you again!");
                // notify an activity finishes without error
                setResult(RESULT_OK, goingBackToMainPageIntent);
                // ends the current activity
                finish();
            }
        });
    }
}
