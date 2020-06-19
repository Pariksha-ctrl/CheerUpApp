package com.example.cheerupapp.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheerupapp.R;
import com.example.cheerupapp.entities.DanceSong;

public class DanceSongViewHolder extends RecyclerView.ViewHolder {

    /*public ImageView danceSongImageView = null;
    public TextView danceSongNameTextView = null;
    public TextView favoriteVerseTextView = null;
    public TextView totalRateTextView = null;*/

    public ImageView danceSongImageView;
    public TextView danceSongNameEditText;
    public TextView danceSongFavoriteVerseEditText;
    public TextView danceSongTotalVotesTextView;
    public RatingBar danceSongRatingBar = null;
    public Button action1Button;

    private  OnDanceSongListener onDanceSongListener;

    // mapping the UI components to the XML layout
    public DanceSongViewHolder(@NonNull View itemView, OnDanceSongListener onDanceSongListener) {
        super(itemView);

        // Linking UI components with xml file tags.
        danceSongImageView = itemView.findViewById(R.id.danceSongImageView);
        danceSongNameEditText = itemView.findViewById(R.id.danceSongNameEditText);
        danceSongFavoriteVerseEditText = itemView.findViewById(R.id.danceSongFavoriteVerseEditText);
        danceSongTotalVotesTextView = itemView.findViewById(R.id.sweetnessTextView);
        danceSongRatingBar = itemView.findViewById(R.id.danceSongRatingBar);
        action1Button = itemView.findViewById(R.id.action1Button);

        this.onDanceSongListener = onDanceSongListener;
    }

    // updating DanceSong with data
    // setting DanceSong objects or data into danceSongViewHolder
    public void updateDanceSong(DanceSong danceSong){

        View rootView = danceSongImageView.getRootView();
        int resID = rootView.getResources().getIdentifier(danceSong.imageFileName, "drawable", rootView.getContext().getPackageName());
        danceSongImageView.setImageResource(resID);
        this.danceSongNameEditText.setText(danceSong.getName());
        this.danceSongFavoriteVerseEditText.setText(danceSong.getFavoriteVerse());
        this.danceSongTotalVotesTextView.setText(danceSong.getVotes() + " Votes");

        float rate;
        if (danceSong.getVotes() > 0){
            rate = 1.0f * danceSong.getStars() / danceSong.getVotes();
        }else {
            rate = 0.0f;
        }
        this.danceSongRatingBar.setRating(rate);
    }

    public void bind(final DanceSong danceSong, final OnDanceSongListener onDanceSongListener){
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDanceSongListener.onDanceSongClick(danceSong);
            }
        });
    }


}
