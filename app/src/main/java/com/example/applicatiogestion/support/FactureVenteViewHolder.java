package com.example.applicatiogestion.support;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.R;
import com.example.applicatiogestion.metier.Factures;

public class FactureVenteViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtFactureVente;

    public FactureVenteViewHolder(final View view){
        super(view);

        txtFactureVente = (TextView) view.findViewById(R.id.txtfactureVenteL);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void remplirViewHolder(Factures laFacture, int position) {
        String holderProd = laFacture.getNom() + " - " + laFacture.getDate() + " - " + laFacture.getPrix_ht();
        Log.i("ViewHolder : ", "Position du clique : " + position);
        txtFactureVente.setText(position + " - " + holderProd);
    }
}
