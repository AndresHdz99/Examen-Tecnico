package com.example.examentecnico.Recyclerview.Categoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.examentecnico.R;

import java.util.List;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.AdapterCategoriaViewHoldel> {
    Context context;
    List<CategoriasG> categoriasGS;

    public AdapterCategoria(Context context, List<CategoriasG> categoriasGS) {
        this.context = context;
        this.categoriasGS = categoriasGS;
    }

    public AdapterCategoriaViewHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_categoria,null);
        return new AdapterCategoriaViewHoldel(view);
    }

    public void onBindViewHolder(@NonNull AdapterCategoriaViewHoldel holder, int position) {
        CategoriasG cate = categoriasGS.get(position);

        holder.categoria.setText(cate.getCategoria());

    }

    public int getItemCount() { return categoriasGS.size(); }

    class AdapterCategoriaViewHoldel extends RecyclerView.ViewHolder {

        TextView categoria;


        public AdapterCategoriaViewHoldel(View itemView){
            super(itemView);

            categoria = itemView.findViewById(R.id.cartegoria);

        }
    }
}
