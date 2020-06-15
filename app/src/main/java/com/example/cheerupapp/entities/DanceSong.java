package com.example.cheerupapp.entities;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class DanceSong implements Serializable {

    // this DANCE_SONG constant is used to identify the DanceSong instance to pass between activities
    public static final String DANCE_SONG_KEY = "dance_song_key";
    public static final String DANCE_SONG_STARS = "dance_song_stars";
    public static final String DANCE_SONG_ID = "dance_song_id";

    //attributes
    private Long id;
    private String name;
    private String favoriteVerse;
    private String danceSongImageFileName;
    private Long rating;
    private Long votes;
    private Long stars;

    public DanceSong() {
    }

    public DanceSong(Long id, String name, String favoriteVerse, String danceSongImageFileName, Long rating, Long votes, Long stars) {
        this.id = id;
        this.name = name;
        this.favoriteVerse = favoriteVerse;
        this.danceSongImageFileName = danceSongImageFileName;
        this.rating = rating;
        this.votes = votes;
        this.stars = stars;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDanceSongImageName() {
        return danceSongImageFileName;
    }

    public void setDanceSongImageName(String danceSongImageName) {
        this.danceSongImageFileName = danceSongImageName;
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
                "id=" + id + System.lineSeparator() +
                ", name='" + name + System.lineSeparator() +
                ", favoriteVerse='" + favoriteVerse + System.lineSeparator() +
                ", danceSongImageName='" + danceSongImageFileName  + System.lineSeparator() +
                ", rating=" + rating + System.lineSeparator() +
                ", votes=" + votes + System.lineSeparator() +
                ", stars=" + stars + System.lineSeparator() +
                '}' + System.lineSeparator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if ((obj == null) || getClass() != obj.getClass()) return false;
        DanceSong danceSong = (DanceSong) obj;
        return id.equals(danceSong.id);
    }
}
