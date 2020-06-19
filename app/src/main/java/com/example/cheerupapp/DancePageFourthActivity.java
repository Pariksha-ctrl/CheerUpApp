package com.example.cheerupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheerupapp.RecyclerView.DanceSongRecyclerViewAdapter;
import com.example.cheerupapp.RecyclerView.OnDanceSongListener;
import com.example.cheerupapp.activities.AddSongForDanceScrollingActivity;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.entities.User;
import com.example.cheerupapp.services.DanceSongDataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.cheerupapp.entities.Constants.ADD_SONG_FOR_DANCE_ACTIVITY_CODE;
import static com.example.cheerupapp.entities.Constants.DANCE_SONG_VIEW_DETAILS_ACTIVITY_CODE;

public class DancePageFourthActivity extends AppCompatActivity implements OnDanceSongListener{

    private List<DanceSong> danceSongs;
    private DanceSongRecyclerViewAdapter danceSongAdapter;
    private DanceSongDataService danceSongDataService;
    private View rootView;
    //private DanceSong danceSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_page_fourth);

        final User user = null;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewDanceSong();
            }
        });

        rootView = findViewById(android.R.id.content).getRootView();

        RecyclerView danceSongRecyclerView = findViewById(R.id.danceSongRecyclerView);

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
        danceSongAdapter = new DanceSongRecyclerViewAdapter(danceSongs, this, (OnDanceSongListener) this);
        //Setting Adapter to the RecyclerView
        danceSongRecyclerView.setAdapter(danceSongAdapter);
    }

    private void addNewDanceSong() {
        Intent goToAddCreateDanceSongIntent = new Intent(this, AddSongForDanceScrollingActivity.class);
        startActivityForResult(goToAddCreateDanceSongIntent, ADD_SONG_FOR_DANCE_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SONG_FOR_DANCE_ACTIVITY_CODE) {
            if (resultCode == RESULT_OK) {
                addDanceSong(data);
            }
        }
        if (requestCode == DANCE_SONG_VIEW_DETAILS_ACTIVITY_CODE) {
            if (resultCode == RESULT_OK) {
                modifyDanceSong(data);
            }
        }
    }

    private void modifyDanceSong(Intent data) {
        Integer stars;
        Long id;

        if (data.hasExtra(DanceSong.DANCE_SONG_KEY) && data.hasExtra(DanceSong.DANCE_SONG_STARS)) {
            DanceSong danceSong = (DanceSong) data.getSerializableExtra(DanceSong.DANCE_SONG_KEY);

            stars = data.getExtras().getInt(DanceSong.DANCE_SONG_STARS);
            id = data.getExtras().getLong(DanceSong.DANCE_SONG_ID);

            if (stars > 0) {
                boolean result = danceSongDataService.rateDanceSong(id, stars);
                int position = danceSongAdapter.getDanceSongs().indexOf(danceSong);
                if (position > 0) {
                    danceSong = danceSongDataService.getDanceSong(id);
                    danceSongAdapter.replaceItem(position, danceSong);
                }
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
        if (result > 0) {
            DanceSong danceSong1 = danceSongDataService.getDanceSong(result);
            danceSongAdapter.addItem(danceSong1);
            resultMessage = "Your dance song was created with id: " + result;
        } else {
            resultMessage = "Your dance song couldn't be created. Try Again!";
        }
        Snackbar.make(rootView, resultMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dance_song_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDanceSongClick(DanceSong danceSong) {
        showDanceSongDetail(danceSong);
    }

    private void showDanceSongDetail(DanceSong danceSong) {
        Intent goToDanceSongDetail = new Intent(this, AddSongForDanceScrollingActivity.class);
        goToDanceSongDetail.putExtra(DanceSong.DANCE_SONG_KEY, danceSong);
        startActivityForResult(goToDanceSongDetail, DANCE_SONG_VIEW_DETAILS_ACTIVITY_CODE);
    }
}
