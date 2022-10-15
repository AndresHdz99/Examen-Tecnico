package com.example.examentecnico.DataBase.Movies;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoMovie {

    @Query("SELECT * FROM movies")
    List<Movies> getMovie();

    @Insert
    void insertMovie(Movies...movies);

    @Query("DELETE FROM movies")
    void deleteMovie();

    @Query("Select count(*) from movies")
    int  getTotal();
}
