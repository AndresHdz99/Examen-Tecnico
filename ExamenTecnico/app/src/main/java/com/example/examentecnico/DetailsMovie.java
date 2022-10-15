package com.example.examentecnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.examentecnico.DataBase.MoviesEnglish.MovieEnglish;
import com.example.examentecnico.DataBase.appDataBase;
import com.example.examentecnico.Recyclerview.Categoria.AdapterCategoria;
import com.example.examentecnico.Recyclerview.Categoria.CategoriasG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailsMovie extends AppCompatActivity {

    ImageView imgmovie;
    TextView titlemoview;

    TextView overview;
    Spinner spinner;

    Bundle recibirtados;

    List<MovieEnglish> movieEnglishes;
    appDataBase db;

    List<CategoriasG> categoriasGS;
    RecyclerView recyclerView;
    AdapterCategoria adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        imgmovie = findViewById(R.id.imagemoviedl);
        titlemoview = findViewById(R.id.titlemoviedl);
        overview =findViewById(R.id.overview);
        spinner = findViewById(R.id.lenguage);

        recyclerView = findViewById(R.id.reciclerCategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        categoriasGS = new ArrayList<>();

        recibirtados = getIntent().getExtras();

        movieEnglishes = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(), appDataBase.class,"dbprueba").allowMainThreadQueries().build();

        selectLenguage();
    }

    private void selectLenguage() {

        String [] lengu = {"Español","Ingles"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, lengu);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccion = adapterView.getItemAtPosition(i).toString();
                if(seleccion.equals("Español")){
                    categoriasGS.clear();
                    getInfoMovie();
                    getCategorias("es");

                }else {
                    categoriasGS.clear();
                    getInfoMovieEnglish();
                    getCategorias("en");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getInfoMovie(){
        String title = recibirtados.getString("titlemovie");
        String overviewm = recibirtados.getString("overviewmoview");
        informationMovie(title,overviewm);
    }

    private void getInfoMovieEnglish(){
        String id = recibirtados.getString("id");
        MovieEnglish movie = db.daoMovieEngli().obtnerMovie(id);
        informationMovie(movie.title,movie.overview);
    }

    private void getCategorias(String idiom){
        String id = recibirtados.getString("id");
        String urlidioma = "https://api.themoviedb.org/3/movie/"+id+"?api_key=cd84bb5c16b067a9e6208b6989b77350&language="+idiom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlidioma, null, new Response.Listener<JSONObject>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("genres");
                    int size = jsonArray.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject movieslist = jsonArray.getJSONObject(i);


                        Log.i("Nombre", movieslist.getString("name"));

                        categoriasGS.add(new CategoriasG(
                                movieslist.getString("name")
                        ));
                        adapter  = new AdapterCategoria(DetailsMovie.this, categoriasGS);

                        recyclerView.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NoConnectionError){
                        recyclerView.setVisibility(View.INVISIBLE);
                }

            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }


    private void informationMovie(String title, String overviewm) {
        String imgmovied = recibirtados.getString("imagemuvie");
        String urlimage = "https://image.tmdb.org/t/p/original"+imgmovied;
        Glide.with(this).load(urlimage).fitCenter().centerCrop().into(imgmovie);
        titlemoview.setText(title);
        overview.setText(overviewm);
    }

}