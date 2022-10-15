package com.example.examentecnico.Recyclerview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examentecnico.DetailsMovie;
import com.example.examentecnico.R;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.AdapterMovieViewHolder> {

    Context context;
    List<MoviesG> moviesGS;

    public AdapterMovie(Context context, List<MoviesG> moviesGS) {
        this.context = context;
        this.moviesGS = moviesGS;
    }

    public AdapterMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movies,null);
        return new AdapterMovieViewHolder(view);
    }

    public void onBindViewHolder(@NonNull AdapterMovieViewHolder holder, int position) {
        MoviesG movie = moviesGS.get(position);
        holder.title.setText(movie.getTitlemovie());

        String urlimage = "https://image.tmdb.org/t/p/original"+movie.getImagmovie();
        Glide.with(context).load(urlimage).fitCenter().centerCrop().into(holder.imgmoview);
        holder.movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviardatos = new Bundle();
                enviardatos.putString("titlemovie",movie.getTitlemovie());
                enviardatos.putString("imagemuvie",movie.getImagmovie());
                enviardatos.putString("overviewmoview",movie.getOverview());
                enviardatos.putString("id",movie.getIdmovie());
                Intent intent = new Intent(context, DetailsMovie.class);
                intent.putExtras(enviardatos);
                context.startActivity(intent);
            }
        });

    }

    public int getItemCount() { return moviesGS.size(); }

    class AdapterMovieViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgmoview;
        CardView movie;


        public AdapterMovieViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            imgmoview = itemView.findViewById(R.id.imgmovie);
            movie = itemView.findViewById(R.id.movie);

        }
    }


}
