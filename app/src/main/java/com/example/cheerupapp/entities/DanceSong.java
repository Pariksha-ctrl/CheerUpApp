package com.example.cheerupapp.entities;

import java.io.Serializable;

public class DanceSong implements Serializable {

    // this DANCE_SONG constant is used to identify the DanceSong instance to pass between activities
    public static final String DANCE_SONG_KEY = "DANCE_SONG";

    //attributes
    private Long id;
    private String name;
    private String favoriteVerse;
    private String singer;
    private String imageName;
    private Long rating;

    public DanceSong(Long id, String name, String favoriteVerse, String singer, String imageName, Long rating) {
        this.id = id;
        this.name = name;
        this.favoriteVerse = favoriteVerse;
        this.singer = singer;
        this.imageName = imageName;
        this.rating = rating;
    }

    public DanceSong() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteVerse() {
        return favoriteVerse;
    }

    public void setFavoriteVerse(String favoriteVerse) {
        this.favoriteVerse = favoriteVerse;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
