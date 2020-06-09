package com.example.cheerupapp.services;

import android.content.Context;

import com.example.cheerupapp.database.DanceSongDatabaseHelper;
import com.example.cheerupapp.entities.DanceSong;

import java.util.List;

public class DanceSongDataService {

    private DanceSongDatabaseHelper sqlite;

    public void init(Context context) {
        sqlite = sqlite.getInstance(context);
    }

    public Long add(DanceSong danceSong) {
        return sqlite.insert(danceSong.getName(), danceSong.getFavoriteVerse(), danceSong.getRating());
    }

    public boolean delete(DanceSong danceSong){

        return sqlite.delete(danceSong.getId());
    }

    public boolean update(DanceSong danceSong){
        return sqlite.update(danceSong.getId(), danceSong.getName(), danceSong.getFavoriteVerse(), danceSong.getRating());
    }

    public List<DanceSong> getDanceSongs(){
        List<DanceSong> danceSongs = sqlite.getAllDanceSongs();
        return danceSongs;
    }

    // this method is used to get a dance song from a database with its primary key - id
    public DanceSong getDanceSong(Long id){
        return null;
    }

    // this method is used to rate dance songs
    public boolean rateDanceSong(Long id, Integer stars){
        return false;
    }
}
