package com.example.cheerupapp.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cheerupapp.R;
import com.example.cheerupapp.entities.DanceSong;

import java.util.List;

public class DanceSongRecyclerViewAdapter extends RecyclerView.Adapter<DanceSongViewHolder>{

    private List<DanceSong> danceSongs;
    private Context context;
    private  OnDanceSongListener onDanceSongListener;

    public List<DanceSong> getDanceSongs(){
        return danceSongs;
    }

    // Constructor
    public DanceSongRecyclerViewAdapter(List<DanceSong> danceSongs, Context context, OnDanceSongListener onDanceSongListener) {
        this.danceSongs = danceSongs;
        this.context = context;
        this.onDanceSongListener = onDanceSongListener;
    }

    @NonNull
    @Override
    public DanceSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create the UI physically form xml we use LayoutInflater class
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View danceSongViewItem = inflater.inflate(R.layout.recycler_dance_song_view, parent, false);

        DanceSongViewHolder danceSongViewHolder = new DanceSongViewHolder(danceSongViewItem, onDanceSongListener);
        return danceSongViewHolder;
    }

    // setting the data to the view holder
    @Override
    public void onBindViewHolder(@NonNull DanceSongViewHolder holder, int position) {
         DanceSong danceSong = danceSongs.get(position);
         holder.updateDanceSong(danceSong);
         holder.bind(danceSong, onDanceSongListener);
    }

    @Override
    public int getItemCount() {
        return danceSongs.size();
    }

    public void addItem(DanceSong danceSong){
        danceSongs.add(danceSong);
        notifyItemInserted(getItemCount());
    }

    public void replaceItem(int position, DanceSong danceSong){
        danceSongs.set(position, danceSong);
        notifyItemChanged(position);
    }

}
