package com.example.cheerupapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cheerupapp.R;
import com.example.cheerupapp.entities.DanceSong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DanceSongDetailScrollingActivity extends AppCompatActivity {

    RatingBar danceSongRatingBar;
    DanceSong danceSong;
    Integer rate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_song_detail_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntentWithData();
            }
        });

        ImageView danceSongImageView= findViewById(R.id.danceSongImageViewDetailActivity);
        TextView danceSongNameTextView = findViewById(R.id.danceSongNameTextViewDetailActivity);
        danceSongRatingBar = findViewById(R.id.danceSongRatingBarDetailActivity);

        Intent intentThatCalled = getIntent();
        if(intentThatCalled.hasExtra(DanceSong.DANCE_SONG_KEY)){
            danceSong = (DanceSong) intentThatCalled.getSerializableExtra(DanceSong.DANCE_SONG_KEY);
        }

        danceSongNameTextView.setText(danceSong.getName());
        danceSongRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = (int) ratingBar.getRating();
            }
        });
        View rootView = danceSongImageView.getRootView();
        int resID = rootView.getResources().getIdentifier(danceSong.imageFileName , "drawable" , rootView.getContext().getPackageName()) ;
        danceSongImageView.setImageResource(resID);
    }

    @Override
    public void onBackPressed() {
        //before you go back do something
        setIntentWithData();
        super.onBackPressed();
    }

    private void setIntentWithData() {
        Intent goingBack = new Intent();
        goingBack.putExtra(DanceSong.DANCE_SONG_KEY, danceSong);
        goingBack.putExtra(DanceSong.DANCE_SONG_STARS,rate );
        goingBack.putExtra(DanceSong.DANCE_SONG_ID, danceSong.getId());

        setResult(RESULT_OK, goingBack);
        finish();
    }

}
