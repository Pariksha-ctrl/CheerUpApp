package com.example.cheerupapp.entities;

import java.io.Serializable;

public class DanceSong implements Serializable {

    // this DANCE_SONG constant is used to identify the DanceSong instance to pass between activities
    public static final String DANCE_SONG_KEY = "DANCE_SONG";

    //attributes
    private Long id;
    private String name;
    private String favoriteVerse;
    private String danceSongImageName;
    private Integer rating;
    private Long votes;
    private Long stars;

    public DanceSong(Long id, String name, String favoriteVerse, String danceSongImageName, Integer rating, Long votes, Long stars) {
        this.id = id;
        this.name = name;
        this.favoriteVerse = favoriteVerse;
        this.danceSongImageName = danceSongImageName;
        this.rating = rating;
        this.votes = votes;
        this.stars = stars;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDanceSongImageName() {
        return danceSongImageName;
    }

    public void setDanceSongImageName(String danceSongImageName) {
        this.danceSongImageName = danceSongImageName;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "DanceSong{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favoriteVerse='" + favoriteVerse + '\'' +
                ", danceSongImageName='" + danceSongImageName + '\'' +
                ", rating=" + rating +
                ", votes=" + votes +
                ", stars=" + stars +
                '}';
    }
}
