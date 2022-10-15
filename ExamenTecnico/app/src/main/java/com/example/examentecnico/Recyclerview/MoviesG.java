package com.example.examentecnico.Recyclerview;

public class MoviesG {


    String titlemovie;
    String overview;
    String imagmovie;
    String idmovie;

    public MoviesG(String titlemovie, String overview, String imagmovie,String idmovie) {
        this.titlemovie = titlemovie;
        this.overview = overview;
        this.imagmovie = imagmovie;
        this.idmovie =  idmovie;

    }

    public String getTitlemovie() {
        return titlemovie;
    }

    public String getOverview() {
        return overview;
    }

    public String getImagmovie() {
        return imagmovie;
    }

    public String getIdmovie() {
        return idmovie;
    }
}
