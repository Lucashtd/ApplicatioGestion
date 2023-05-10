package com.example.applicatiogestion.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.R;
import com.example.applicatiogestion.metier.Factures;

import java.util.ArrayList;
import java.util.List;

public class FactureVenteAdapter extends RecyclerView.Adapter {

    List<Factures> lesFactures = new ArrayList<Factures>();

    public FactureVenteAdapter(List<Factures> lesFactures){ this.lesFactures = lesFactures;}

    @NonNull
    @Override
    public FactureVenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.facture_vente_liste, parent, false);
        return new FactureVenteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Factures laFacture = lesFactures.get(position);
        ((FactureVenteViewHolder)holder).remplirViewHolder(laFacture, position);
    }

    @Override
    public int getItemCount() {
        if(lesFactures != null){
            return lesFactures.size();
        }
        else return 0;
    }
}
