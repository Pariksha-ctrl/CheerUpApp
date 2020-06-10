package com.example.cheerupapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheerupapp.R;
import com.example.cheerupapp.RecyclerView.DanceSongRecyclerViewAdapter;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.services.DanceSongDataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.cheerupapp.entities.Constants.ADD_SONG_FOR_DANCE_ACTIVITY_CODE;

public class DanceSongContent extends AppCompatActivity {

    private List<DanceSong> danceSongs;
    private DanceSongRecyclerViewAdapter danceSongAdapter;
    private DanceSongDataService danceSongDataService;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_song_content);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSongForDance();
            }
        });

        rootView = findViewById(android.R.id.content).getRootView();

        RecyclerView danceSongRecyclerView = findViewById(R.id.danceSongsRecyclerView);

        // set the layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        danceSongRecyclerView.setLayoutManager(linearLayoutManager);

        // initializing the database
        danceSongDataService = new DanceSongDataService();
        danceSongDataService.init(this);

        //Call database to get all the danceSong
        danceSongs = danceSongDataService.getDanceSongs();
        //Creating a RecyclerViewAdapter and passing the data
        danceSongAdapter = new DanceSongRecyclerViewAdapter(danceSongs, this);
        //Setting Adapter to the RecyclerView
        danceSongRecyclerView.setAdapter(danceSongAdapter);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void addNewSongForDance() {
        Intent goToAddSongForDanceScrollingActivityIntent = new Intent(DanceSongContent.this, AddSongForDanceScrollingActivity.class);
        startActivityForResult(goToAddSongForDanceScrollingActivityIntent, ADD_SONG_FOR_DANCE_ACTIVITY_CODE);
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
        // to identify which class data we are getting
        // now we are getting dance song in here
        String resultMessage;
        DanceSong danceSong = (DanceSong) data.getSerializableExtra(DanceSong.DANCE_SONG_KEY);
        // we need to persist this in database
        Long result = danceSongDataService.add(danceSong);
        // checking if an id is less than zero which means something is wrong in there.
        if (result > 0){

            DanceSong danceSong1 = danceSongDataService.getDanceSong(result);
            danceSongAdapter.addItem(danceSong1);

            resultMessage = "Your dance song was created with id: "+result;
        }else {
            resultMessage = "Your dance song couldn't be created. Try Again!";
        }
        Snackbar.make(rootView, resultMessage, Snackbar.LENGTH_SHORT).show();
    }

}
