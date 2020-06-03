package com.example.cheerupapp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheerupapp.R;
import com.example.cheerupapp.RecyclerView.DanceSongRecyclerViewAdapter;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.services.DanceSongDataService;

import java.util.List;

public class DanceSongContent extends AppCompatActivity {

    private DanceSongDataService danceSongDataService;
    private View rootView;
    private DanceSong danceSong;
    private List<DanceSong> danceSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_song_content);

        // initializing the database
        danceSongDataService = new DanceSongDataService();
        danceSongDataService.init(DanceSongContent.this);

        rootView = findViewById(android.R.id.content).getRootView();

        RecyclerView danceSongRecyclerView = findViewById(R.id.danceSongRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // GridLayoutManager
        danceSongRecyclerView.setLayoutManager(linearLayoutManager);

        //Call database to get all the danceSong
        danceSongs = danceSongDataService.getDanceSongs();

        //Creating a RecyclerViewAdapter and passing the data
        DanceSongRecyclerViewAdapter danceSongAdapter = new DanceSongRecyclerViewAdapter(danceSongs, this);

        //Setting Adapter to the RecyclerView
        danceSongRecyclerView.setAdapter(danceSongAdapter);

    }

    private void viewAll(View v) {
        List<DanceSong> danceSongs = danceSongDataService.getDanceSongs();
        String text = "";

        if (danceSongs.size() > 0) {
            for (DanceSong danceSong : danceSongs) {
                text = text.concat(danceSong.toString());
            }
            showMessage("Data", text);
        } else {
            showMessage("Records", "Nothing found");
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
