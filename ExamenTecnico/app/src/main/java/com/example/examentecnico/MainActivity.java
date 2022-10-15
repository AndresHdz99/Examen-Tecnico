package com.example.examentecnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.examentecnico.DataBase.MoviesEnglish.MovieEnglish;
import com.example.examentecnico.DataBase.appDataBase;
import com.example.examentecnico.DataBase.Movies.Movies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    private IntentFilter intentFilter;
    private ChargBrodcasReceiver chargBrodcasReceiver;


    List<Movies> movies;
    List<MovieEnglish> movieEnglishes;
    appDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        movies = new ArrayList<>();
        movieEnglishes = new ArrayList<>();
        db = Room.databaseBuilder(getApplicationContext(), appDataBase.class,"dbprueba").allowMainThreadQueries().build();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int numero = db.daoMovie().getTotal();
                //Toast.makeText(MainActivity.this, "Cantidad"+ numero, Toast.LENGTH_SHORT).show();
                if(numero == 0){
                    getMovies();
                    Log.i("Cambio de fecha ","Se Agregaron peliculas");

                }else {
                    Intent intent = new Intent(MainActivity.this,MoviesPrincipal.class);
                    startActivity(intent);
                   // Toast.makeText(MainActivity.this, "Si hay regristros", Toast.LENGTH_SHORT).show();
                }
            }
        },2000);


        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        chargBrodcasReceiver = new ChargBrodcasReceiver();


    }

    private class ChargBrodcasReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean fecha = (action.equals(Intent.ACTION_DATE_CHANGED));
            showFecha(fecha);
        }
    }

    private void showFecha(boolean fecha) {
        if(fecha){
           // Toast.makeText(MainActivity.this, "Cambio la fecha", Toast.LENGTH_SHORT).show();
            Log.i("FEcha","Fecha cambiada");
            db.daoMovie().deleteMovie();
            db.daoMovieEngli().deleteMovie();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.i("Reinicio","Fecha cambiada");
                }
            },2000);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(chargBrodcasReceiver,intentFilter);
    }

    private void getMovies() {
        String urlmovies ="https://api.themoviedb.org/3/movie/top_rated?api_key=cd84bb5c16b067a9e6208b6989b77350&language=es";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlmovies, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i< 10; i++){
                        JSONObject movieslist = jsonArray.getJSONObject(i);

                        db.daoMovie().insertMovie(new Movies(
                                movieslist.getString("id"),
                                movieslist.getString("title"),
                                movieslist.getString("overview"),
                                movieslist.getString("poster_path")
                                ));

                    }
                   // Toast.makeText(MainActivity.this, "LLenado principal", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getMoviesEnglish();
                        }
                    },100);

                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);


    }

    private void getMoviesEnglish() {

        String urlmovies ="https://api.themoviedb.org/3/movie/top_rated?api_key=cd84bb5c16b067a9e6208b6989b77350&language=en";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlmovies, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i< 10; i++){
                        JSONObject movieslist = jsonArray.getJSONObject(i);

                        db.daoMovieEngli().insertMoviein(new MovieEnglish(
                                movieslist.getString("id"),
                                movieslist.getString("title"),
                                movieslist.getString("overview")));

                    }
                  //  Toast.makeText(MainActivity.this, "LLenado ingles", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this,MoviesPrincipal.class);
                            startActivity(intent);
                        }
                    },100);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }


}