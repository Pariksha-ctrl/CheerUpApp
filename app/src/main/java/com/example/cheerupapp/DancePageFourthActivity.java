package com.example.cheerupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cheerupapp.RecyclerView.DanceSongRecyclerViewAdapter;
import com.example.cheerupapp.activities.AddSongForDanceScrollingActivity;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.entities.User;
import com.example.cheerupapp.services.DanceSongDataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.cheerupapp.entities.Constants.ADD_SONG_FOR_DANCE_ACTIVITY_CODE;

public class DancePageFourthActivity extends AppCompatActivity {

    TextView danceSongEditText;
    Button clearButton;
    Button viewAllButton;
    Button updateButton;
    Button deleteButton;
    Button goBackToMorningActivityButton;

    private List<DanceSong> danceSongs;
    private DanceSongRecyclerViewAdapter danceSongAdapter;
    private DanceSongDataService danceSongDataService;
    private View rootView;
    private EditText danceSongIdEditText;
    private DanceSong danceSong;


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
        danceSongEditText = findViewById(R.id.danceSongIdEditText);

/*        RecyclerView danceSongRecyclerView = findViewById(R.id.danceSongsRecyclerView);

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
        danceSongAdapter = new DanceSongRecyclerViewAdapter(danceSongs, this, this);
        //Setting Adapter to the RecyclerView
        danceSongRecyclerView.setAdapter(danceSongAdapter);*/

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });

        viewAllButton = findViewById(R.id.viewAllButton);
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll(v);
            }
        });

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

        goBackToMorningActivityButton = findViewById(R.id.goBackToMorningActivityButton);
        goBackToMorningActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goingBackToMorningActivityPageIntent = new Intent();
                goingBackToMorningActivityPageIntent.putExtra(User.USER_KEY, user);
                // notify an activity finishes without error
                setResult(RESULT_OK, goingBackToMorningActivityPageIntent);
                // ends the current activity
                finish();
            }
        });

        //Load Data from the database
        danceSongDataService = new DanceSongDataService();
        danceSongDataService.init(this);
    }

    // DELETE
    private void delete(View v) {
        String id = danceSongEditText.getText().toString();
        if (isDanceSongsIdEmpty(v, id))
            return;

        danceSong = new DanceSong();
        danceSong.setId(Long.valueOf(id));

        boolean result = danceSongDataService.delete(danceSong);

        if (result)
            Snackbar.make(v, "Dance Song id" + id + " was deleted ", Snackbar.LENGTH_SHORT).show();
        else
            Snackbar.make(v, "Error, Dance song id " + id + " was not deleted ", Snackbar.LENGTH_SHORT).show();
    }

    // UPDATE
    private void update(View v) {
        String id = danceSongIdEditText.getText().toString();
        if (isDanceSongsIdEmpty(v, id))
            return;
        Snackbar.make(v, "To be implemented", Snackbar.LENGTH_SHORT).show();
    }

    // VIEW ALL
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

    private void clear(View v) {
        danceSongIdEditText.getText().clear();

    }

    private boolean isDanceSongsIdEmpty(View view, String id) {
        // clean empty spaces and trailing and check if the size is not zero
        if (id.trim().isEmpty()) {
            Snackbar.make(view, "You should input dance song's Id", Snackbar.LENGTH_SHORT).show();
            danceSongEditText.requestFocus();
            return true;
        }
        return false;
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void addNewDanceSong() {
        Intent goToAddCreateDanceSongIntent = new Intent(this, AddSongForDanceScrollingActivity.class);
        startActivityForResult(goToAddCreateDanceSongIntent, ADD_SONG_FOR_DANCE_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_SONG_FOR_DANCE_ACTIVITY_CODE){
            if (resultCode == RESULT_OK){
                addDanceSong(data);
            }
        }
/*        if( requestCode == DANCE_SONG_VIEW_DETAILS_ACTIVITY_CODE){
            if(resultCode == RESULT_OK){
                modifyDanceSong(data);
            }
        }*/
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

            /*DanceSong danceSong1 = danceSongDataService.getDanceSong(result);
            danceSongAdapter.addItem(danceSong1);*/

            resultMessage = "Your dance song was created with id: "+result;
        }else {
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

        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


/*
    private void modifyDanceSong(Intent data) {
        Integer stars;
        Long id;

        if (data.hasExtra(DanceSong.DANCE_SONG_KEY) && data.hasExtra(DanceSong.DANCE_SONG_STARS)){
            DanceSong danceSong = (DanceSong)data.getSerializableExtra(DanceSong.DANCE_SONG_KEY);

            stars = data.getExtras().getInt(DanceSong.DANCE_SONG_STARS);
            id = data.getExtras().getLong(DanceSong.DANCE_SONG_ID);

            if (stars > 0){
                boolean result = danceSongDataService.rateDanceSong(id, stars);
                int position = danceSongAdapter.getDanceSongs().indexOf(danceSong);
                if (position > 0){
                    danceSong = danceSongDataService.getDanceSong(id);
                    danceSongAdapter.replaceItem(position, danceSong);
                }
            }
        }
    }

    public void onDanceSongClick(DanceSong danceSong) {
        showDanceSongDetail(danceSong);
    }

    private void showDanceSongDetail(DanceSong danceSong) {
        Intent goToDanceSongDetail = new Intent(this, AddSongForDanceScrollingActivity.class);
        goToDanceSongDetail.putExtra(DanceSong.DANCE_SONG_KEY, danceSong);
        startActivityForResult(goToDanceSongDetail, DANCE_SONG_VIEW_DETAILS_ACTIVITY_CODE);

    }*/


}
