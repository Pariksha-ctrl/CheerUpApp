package com.example.cheerupapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.cheerupapp.R;
import com.example.cheerupapp.entities.DanceSong;
import com.google.android.material.snackbar.Snackbar;

public class AddSongForDanceScrollingActivity extends AppCompatActivity {

    private EditText songNameEditText;
    private EditText addFavoriteVerseEditText;
    private SeekBar ratingSeekBar;
    private Button cancelSongButton;
    private Button addDanceSongButton;
    private Long ratingValue = 0L;
    private DanceSong danceSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song_scrolling);

        songNameEditText = findViewById(R.id.songNameEditText);
        addFavoriteVerseEditText = findViewById(R.id.addFavoriteVerseEditText);
        ratingSeekBar = findViewById(R.id.ratingSeekBar);
        cancelSongButton = findViewById(R.id.cancelSongButton);
        addDanceSongButton = findViewById(R.id.addDanceSongButton);

        addDanceSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDanceSong(v);
            }
        });

        cancelSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });

        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratingValue = Long.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void cancel(View v) {
    }

    private void addDanceSong(View v) {
        String songName = songNameEditText.getText().toString();
        //this trim method is to get rid of every empty spaces
        if (songName.trim().isEmpty()){
            Snackbar.make(v, "Song name is required", Snackbar.LENGTH_SHORT).show();
            songNameEditText.getText().clear();
            songNameEditText.requestFocus();
            return;
        }
        String favoriteVerse = addFavoriteVerseEditText.getText().toString().trim();

        danceSong = new DanceSong();
        danceSong.setName(songName);
        danceSong.setFavoriteVerse(favoriteVerse);
        danceSong.setRating(ratingValue);

        //after we click add, we need to close this activity and go back to Dance page fifth activity
        // for that we need to create intent
        Intent goingBackToDancePage = new Intent();
        goingBackToDancePage.putExtra();
    }
}
