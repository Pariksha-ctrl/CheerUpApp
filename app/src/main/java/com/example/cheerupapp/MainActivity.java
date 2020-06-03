package com.example.cheerupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cheerupapp.entities.User;

import static com.example.cheerupapp.entities.Constants.REQUEST_CODE_WELCOME_PAGE_SECOND_SCREEN;

public class MainActivity extends AppCompatActivity {

    Button letsGetStartedButton;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.userNameEditText);
        letsGetStartedButton = findViewById(R.id.letsGetStartedButton);

        letsGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this states our intention on what we want to do
                Intent goToWelcomePageIntent = new Intent(MainActivity.this, WelcomePageSecondActivity.class);
                String userName = userNameEditText.getText().toString();
                // create an instance of User
                User user = new User();
                user.setName(userName);

                // to show the user name on Welcome Page
                goToWelcomePageIntent.putExtra(User.USER_KEY, user);

                //goToWelcomePageIntent.putExtra("USER_GREETING", "Good Morning");
                //goToWelcomePageIntent.putExtra("USER_NAME", "Pariksha");

                //String userName = userNameEditText.getText().toString();

                // if we are not getting any values or data back
                //startActivity(goToWelcomePageIntent);

                // if we are getting any values or data back

                startActivityForResult(goToWelcomePageIntent, REQUEST_CODE_WELCOME_PAGE_SECOND_SCREEN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WELCOME_PAGE_SECOND_SCREEN){
            if (resultCode == RESULT_OK){
                // if all good
                String messagePassedBackToLeavingUser = data.getStringExtra("USER_LEAVING_MESSAGE");
                Toast.makeText(this, messagePassedBackToLeavingUser, Toast.LENGTH_SHORT).show();
            }
        }

    }
}























