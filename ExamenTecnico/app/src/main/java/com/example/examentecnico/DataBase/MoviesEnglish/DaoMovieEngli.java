package com.example.examentecnico.DataBase.MoviesEnglish;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoMovieEngli {

    @Query("SELECT * FROM movieenglish")
    List<MovieEnglish> MovieEnglish();

    @Query("SELECT * FROM movieenglish WHERE id = :id ")
    MovieEnglish obtnerMovie(String id);

    @Insert
    void insertMoviein(MovieEnglish...movieEnglishes);


    @Query("DELETE FROM movieenglish")
    void deleteMovie();
}
