package com.example.applicatiogestion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicatiogestion.DAO.GestionBD;
import com.example.applicatiogestion.metier.Produits;

public class CreationProduitActivity extends AppCompatActivity {
    GestionBD db = new GestionBD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_produits);

        EditText edtNom = findViewById(R.id.edtProduitNom);
        EditText edtPrix = findViewById(R.id.edtProduitPrix);
        EditText edtRef = findViewById(R.id.edtProduitRef);
        Button btnRetourGestProd2 = findViewById(R.id.btnRetourGestProd2);
        Button btnCreerProduit = findViewById(R.id.btnValiderCreerProduit);

        btnRetourGestProd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestProd = new Intent(view.getContext(), GestionProduitActivity.class);
                view.getContext().startActivity(gestProd);
            }
        });

        btnCreerProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leNom = edtNom.getText().toString();
                int lePrix = Integer.parseInt(edtPrix.getText().toString());
                String laRef = edtRef.getText().toString();

                db.open();
                Produits produits = new Produits(leNom, lePrix, laRef);
                Log.i("Main ", "Creer produit : " + produits.toString());
                db.ajoutProduit(produits);
                db.close();
            }
        });
    }
}
