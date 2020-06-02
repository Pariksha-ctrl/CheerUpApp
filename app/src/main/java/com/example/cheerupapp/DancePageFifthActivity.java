package com.example.cheerupapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cheerupapp.activities.AddSongForDanceScrollingActivity;
import com.example.cheerupapp.entities.DanceSong;
import com.example.cheerupapp.services.DanceSongDataService;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.cheerupapp.entities.Constants.ADD_SONG_FOR_DANCE_ACTIVITY_CODE;


public class DancePageFifthActivity extends AppCompatActivity {

    TextView danceSongEditText;
    Button addNewSongForDanceButton;
    Button clearButton;
    Button viewAllButton;
    Button updateButton;
    Button deleteButton;
    Button goBackToMorningActivityButton;

    private DanceSongDataService danceSongDataService;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_page_fifth);

        final User user = null;

        rootView = findViewById(android.R.id.content).getRootView();

        addNewSongForDanceButton = findViewById(R.id.addNewSongForDanceButton);

        addNewSongForDanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSongForDance();
            }
        });

        danceSongEditText = findViewById(R.id.danceSongEditText);

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear(v);
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

        // initializing the database
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

/*    private void clear(View v) {
        danceSongEditText.getText().clear();
    }*/

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


    private void update(View v) {
        String id = danceSongEditText.getText().toString();
        if (isDanceSongsIdEmpty(v, id))
            return;
        Snackbar.make(v, "To be implemented", Snackbar.LENGTH_SHORT).show();
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

    private void delete(View v) {
        String id = danceSongEditText.getText().toString();
        if (isDanceSongsIdEmpty(v, id))
            return;

        DanceSong danceSong = new DanceSong();
        danceSong.setId(Long.valueOf(id));

        boolean result = danceSongDataService.delete(danceSong);

        if (result)
            Snackbar.make(v, "Dance Song id" + id + " was deleted ", Snackbar.LENGTH_SHORT).show();
        else
            Snackbar.make(v, "Error, Dance song id " + id + " was not deleted ", Snackbar.LENGTH_SHORT).show();
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
        // to identify which class data we are getting
        // now we are getting dance song in here
        String resultMessage;
        DanceSong danceSong = (DanceSong) data.getSerializableExtra(DanceSong.DANCE_SONG_KEY);
        // we need to persist this in database
        Long result = danceSongDataService.add(danceSong);
        // checking if an id is less than zero which means something is wrong in there.
        if (result > 0){
            resultMessage = "Your dance song was created with id: "+result;
        }else {
            resultMessage = "Your dance song couldn't be created. Try Again!";
        }
        Snackbar.make(rootView, resultMessage, Snackbar.LENGTH_SHORT).show();
    }

    /*    private void setSupportActionBar(Toolbar toolbar) {
    }*/
}
