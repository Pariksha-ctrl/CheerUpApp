package com.example.cheerupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cheerupapp.entities.User;

import static com.example.cheerupapp.entities.Constants.REQUEST_CODE_MORNING_ACTIVITIES_PAGE_THIRD_SCREEN;

public class WelcomePageSecondActivity extends AppCompatActivity {

    TextView userNameTextView;
    Button goBackToMainPageButton;
    Button letsDanceToCheerUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_second);

        final User user;

        Intent userCallerIntent = getIntent();
        user = (User) userCallerIntent.getSerializableExtra(User.USER_KEY);

        final TextView userNameTextView = findViewById(R.id.userGreetingTextView);
        userNameTextView.setText(user.getName());

        letsDanceToCheerUpButton = findViewById(R.id.letsDanceToCheerUpButton);

        letsDanceToCheerUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // stating our intention
                Intent goToMorningActivitiesPageIntent = new Intent(WelcomePageSecondActivity.this, MorningActivitiesPageThirdActivity.class);
                String userName = userNameTextView.getText().toString();
                // create an instance of User
                User user = new User();
                user.setName(userName);
                // to show the user name on Morning Activities Page
                goToMorningActivitiesPageIntent.putExtra(User.USER_KEY, user);
                startActivity(goToMorningActivitiesPageIntent);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MORNING_ACTIVITIES_PAGE_THIRD_SCREEN){
          if (resultCode == RESULT_OK){
                // if all good
            }
        }

    }

}
