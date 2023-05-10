package com.example.applicatiogestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applicatiogestion.DAO.BDHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGestionProduits = findViewById(R.id.btnGestionProduits);
        Button btnGestionFactures = findViewById(R.id.btnGestionFactures);

        btnGestionProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestionProduits = new Intent(view.getContext(), GestionProduitActivity.class);
                view.getContext().startActivity(gestionProduits);
            }
        });

        btnGestionFactures.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent gestionFactures = new Intent(view.getContext(), GestionFactureActivity.class);
                view.getContext().startActivity(gestionFactures);
            }
        });
    }
}