package com.example.applicatiogestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.DAO.GestionBD;
import com.example.applicatiogestion.metier.Produits;

import java.util.ArrayList;
import java.util.List;

public class GestionFactureActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_facture);
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        Button btnFactureAchat = findViewById(R.id.btnFactureAchat);
        Button btnFactureVente = findViewById(R.id.btnFactureVente);
        Button btnRetourGF = findViewById(R.id.btnRetourGF);

        btnRetourGF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retourGF = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(retourGF);
            }
        });

        btnFactureVente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent factureVente = new Intent(view.getContext(), FactureVenteActivity.class);
                view.getContext().startActivity(factureVente);
            }
        });

    }
}
