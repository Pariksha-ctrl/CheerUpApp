package com.example.cheerupapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cheerupapp.R;
import com.example.cheerupapp.entities.DanceSong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AddSongForDanceScrollingActivity extends AppCompatActivity {

    private EditText danceSongNameEditText;
    private EditText addFavoriteVerseEditText;
    private SeekBar sweetnessSeekBar;
    private Button cancelSongButton;
    private Button addDanceSongButton;

    private DanceSong danceSong;
    private Integer sweetnessValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dance_song_scrolling);

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

        danceSongNameEditText = findViewById(R.id.danceSongNameEditText);
        addFavoriteVerseEditText = findViewById(R.id.addFavoriteVerseEditText);
        sweetnessSeekBar = findViewById(R.id.sweetnessSeekBar);
        cancelSongButton = findViewById(R.id.cancelSongButton);
        addDanceSongButton = findViewById(R.id.addDanceSongButton);

        cancelSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });

        addDanceSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDanceSong(v);
            }
        });

        sweetnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sweetnessValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void addDanceSong(View v) {
        String songName = danceSongNameEditText.getText().toString();
        //this trim method is to get rid of every empty spaces
        if (songName.trim().isEmpty()){
            Snackbar.make(v, "Song name is required", Snackbar.LENGTH_SHORT).show();
            danceSongNameEditText.getText().clear();
            danceSongNameEditText.requestFocus();
            return;
        }
        String favoriteVerse = addFavoriteVerseEditText.getText().toString().trim();
        danceSong = new DanceSong();
        danceSong.setName(songName);
        danceSong.setFavoriteVerse(favoriteVerse);
        danceSong.setSweetness(sweetnessValue);

        //after we click add, we need to close this activity and go back to Dance page fifth activity
        // for that we need to create intent
        Intent goingBackIntent = new Intent();
        goingBackIntent.putExtra(DanceSong.DANCE_SONG_KEY, danceSong);
        setResult(RESULT_OK, goingBackIntent);
        finish();
    }

    private void cancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
