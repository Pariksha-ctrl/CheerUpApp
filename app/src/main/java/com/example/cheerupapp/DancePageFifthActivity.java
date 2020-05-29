package com.example.cheerupapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cheerupapp.activities.AddSongForDanceScrollingActivity;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.services.DanceSongDataService;

import static com.example.cheerupapp.entities.Constants.ADD_SONG_FOR_DANCE_ACTIVITY_CODE;


public class DancePageFifthActivity extends AppCompatActivity {

    Button addNewSongForDanceButton;

    private DanceSongDataService danceSongDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_page_fifth);

        addNewSongForDanceButton = findViewById(R.id.addNewSongForDanceButton);

        addNewSongForDanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSongForDance();
            }
        });

        danceSongDataService = new DanceSongDataService();
        danceSongDataService.init(DancePageFifthActivity.this);

/*        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
     v       @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void addNewSongForDance() {
        Intent goToAddCreateSongForDanceIntent = new Intent(DancePageFifthActivity.this, AddSongForDanceScrollingActivity.class);
        startActivityForResult(goToAddCreateSongForDanceIntent, ADD_SONG_FOR_DANCE_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_SONG_FOR_DANCE_ACTIVITY_CODE){
            if (resultCode == RESULT_OK){
                addDanceSong(data);
            }
        }
    }

    private void addDanceSong(Intent data) {
        DanceSong danceSong = (DanceSong) data.getSerializableExtra(DanceSong.DANCE_SONG_KEY);
        // we need to persist this in database
        danceSongDataService.add(danceSong);

    }

    /*    private void setSupportActionBar(Toolbar toolbar) {
    }*/
}
