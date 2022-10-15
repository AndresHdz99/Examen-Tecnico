package com.example.examentecnico.DataBase.MoviesEnglish;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MovieEnglish {

    @PrimaryKey
    @NonNull
    public String id;
    public String title;
    public String overview;

    public MovieEnglish(@NonNull String id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }
}
