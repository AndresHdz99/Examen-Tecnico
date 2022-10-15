package com.example.examentecnico.DataBase.Movies;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movies {

    @PrimaryKey
    @NonNull
    public String id;
    public String title;
    public String overview;
    public String poster_path;

    public Movies(@NonNull String id, String title, String overview, String poster_path) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
    }
}
