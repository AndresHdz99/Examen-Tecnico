package com.example.examentecnico.Recyclerview.Categoria;

public class CategoriasG {
    String categoria;
    Boolean Categorias;

    public CategoriasG(Boolean categorias) {
        Categorias = categorias;
    }

    public Boolean getCategorias() {
        return Categorias;
    }

    public CategoriasG(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
