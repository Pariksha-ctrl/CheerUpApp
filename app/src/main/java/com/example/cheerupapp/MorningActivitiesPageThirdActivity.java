package com.example.cheerupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.cheerupapp.entities.Constants.REQUEST_CODE_DANCE_PAGE_FOURTH_SCREEN;

public class MorningActivitiesPageThirdActivity extends AppCompatActivity {

    TextView userGreetingTextView;
    Button danceButton;
    Button goBackToWelcomePageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_activities_page_third);

        userGreetingTextView = findViewById(R.id.userGreetingTextView);
        userGreetingTextView.setText("Hi There!");

        danceButton = findViewById(R.id.danceButton);

        danceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDancePageIntent = new Intent(MorningActivitiesPageThirdActivity.this, DancePageFourthActivity.class);
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
                 // All Good
            }
        }
    }
}
