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
    public Long id;
    public String name;
    public String favoriteVerse;
    public String imageFileName;
    public Integer sweetness;
    private Long votes;
    private Long stars;

    public DanceSong() {
    }

    public DanceSong(Long id, String name, String favoriteVerse, String imageFileName, Integer sweetness, Long votes, Long stars) {
        this.id = id;
        this.name = name;
        this.favoriteVerse = favoriteVerse;
        this.imageFileName = imageFileName;
        this.sweetness = sweetness;
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

    public Integer getSweetness() {
        return sweetness;
    }

    public void setSweetness(Integer rating) {
        this.sweetness = sweetness;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
                ", imageFileName='" + imageFileName  + '\'' +
                ", sweetness=" + sweetness +
                ", votes=" + votes +
                ", stars=" + stars +
                '}';
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if ((obj == null) || getClass() != obj.getClass()) return false;
        DanceSong danceSong = (DanceSong) obj;
        return id.equals(danceSong.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
