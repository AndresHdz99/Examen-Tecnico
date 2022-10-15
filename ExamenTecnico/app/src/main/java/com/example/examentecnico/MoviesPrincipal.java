package com.example.examentecnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.examentecnico.DataBase.Movies.Movies;
import com.example.examentecnico.DataBase.appDataBase;
import com.example.examentecnico.Recyclerview.AdapterMovie;
import com.example.examentecnico.Recyclerview.MoviesG;

import java.util.ArrayList;
import java.util.List;

public class MoviesPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MoviesG> moviesGS;
    List<Movies> movies;
    appDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.recyclermovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        moviesGS = new ArrayList<>();
        movies = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(), appDataBase.class,"dbprueba").allowMainThreadQueries().build();

        recycler();
    }

    private void recycler(){
        movies = db.daoMovie().getMovie();
        for (int i = 0; i < movies.size(); i++){
            moviesGS.add(new MoviesG(
                    movies.get(i).title,
                    movies.get(i).overview,
                    movies.get(i).poster_path,
                    movies.get(i).id
                    ));
        }
        AdapterMovie adapter = new AdapterMovie(MoviesPrincipal.this, moviesGS);
        recyclerView.setAdapter(adapter);
    }

}