package com.example.applicatiogestion.support;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.DetailProduitActivity;
import com.example.applicatiogestion.R;
import com.example.applicatiogestion.metier.Produits;

public class ProduitViewHolder extends RecyclerView.ViewHolder {
    private final TextView txtProduits;

    public ProduitViewHolder(final View view){
        super(view);

        txtProduits = (TextView) view.findViewById(R.id.txtProduit);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailProduit = new Intent(view.getContext(), DetailProduitActivity.class);
                String leProduit = txtProduits.getText().toString();
                String [] lesInfos = leProduit.split(" - ");
                detailProduit.putExtra("laRef", lesInfos[3]);
                detailProduit.putExtra("lePrix", lesInfos[2]);
                detailProduit.putExtra("leNom",lesInfos[1]);
                detailProduit.putExtra("laPosition",lesInfos[0]);
                view.getContext().startActivity(detailProduit);
            }
        });
    }

    public void remplirViewHolder(Produits leProduit, int position) {
        String holderProd = leProduit.getNom() + " - " + leProduit.getPrixunitaire() + " - " + leProduit.getRef();
        Log.i("ViewHolder : ", "Position du clique : " + position);
        txtProduits.setText(position + " - " + holderProd);
    }
}
