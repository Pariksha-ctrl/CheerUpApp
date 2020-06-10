package com.example.cheerupapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
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
    public TextView danceSongNameTextView;
    public TextView favoriteVerseTextView;
    public TextView totalRateTextView;


    // mapping the UI components to the XML layout
    public DanceSongViewHolder(@NonNull View itemView) {
        super(itemView);
        // Linking UI components with xml file tags.
        danceSongImageView = itemView.findViewById(R.id.danceSongImageView);
        danceSongNameTextView = itemView.findViewById(R.id.danceSongNameTextView);
        favoriteVerseTextView = itemView.findViewById(R.id.favoriteVerseTextView);
        totalRateTextView = itemView.findViewById(R.id.totalRateTextView);
    }

    // updating DanceSong with data
    // setting DanceSong objects or data into danceSongViewHolder
    public void updateDanceSong(DanceSong danceSong){
        View rootView = danceSongImageView.getRootView();
        danceSongNameTextView.setText(danceSong.getName());
        favoriteVerseTextView.setText(danceSong.getFavoriteVerse());
        totalRateTextView.setText(danceSong.getRating() + " Ratings");
        int resID = rootView.getResources().getIdentifier(danceSong.getDanceSongImageName(), "drawable", rootView.getContext().getPackageName());
        danceSongImageView.setImageResource(resID);
    }
}
