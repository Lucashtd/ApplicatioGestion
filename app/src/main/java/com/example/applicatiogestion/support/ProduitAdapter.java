package com.example.applicatiogestion.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.R;
import com.example.applicatiogestion.metier.Produits;

import java.util.ArrayList;
import java.util.List;

public class ProduitAdapter extends RecyclerView.Adapter {
    List<Produits> lesProduits = new ArrayList<Produits>();

    public ProduitAdapter(List<Produits> lesProduits){ this.lesProduits = lesProduits;}

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.produit_list, parent, false);
        return new ProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Produits leProduit = lesProduits.get(position);
        ((ProduitViewHolder)holder).remplirViewHolder(leProduit, position);
    }

    @Override
    public int getItemCount() {
        if(lesProduits != null){
            return lesProduits.size();
        }
        else return 0;
    }
}
