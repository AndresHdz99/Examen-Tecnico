package com.example.examentecnico.DataBase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.examentecnico.DataBase.Movies.DaoMovie;
import com.example.examentecnico.DataBase.Movies.Movies;
import com.example.examentecnico.DataBase.MoviesEnglish.DaoMovieEngli;
import com.example.examentecnico.DataBase.MoviesEnglish.MovieEnglish;

@Database(
        entities = {Movies.class, MovieEnglish.class},
        version = 5
)

public abstract class appDataBase extends RoomDatabase {

    public abstract DaoMovie daoMovie();
    public abstract DaoMovieEngli daoMovieEngli();
}
