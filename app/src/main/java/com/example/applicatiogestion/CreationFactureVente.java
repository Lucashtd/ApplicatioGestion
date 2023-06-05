package com.example.applicatiogestion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicatiogestion.DAO.GestionBD;
import com.example.applicatiogestion.metier.Factures;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreationFactureVente extends AppCompatActivity {
    GestionBD db = new GestionBD(this);

    protected void OnCreate(Bundle saveInstantState){
        super.onCreate(saveInstantState);
        setContentView(R.layout.creation_facture_vente);

        String type = "Vente";
        EditText edtdate = findViewById(R.id.edtDateFactVente);
        EditText edtprixHT = findViewById(R.id.edtPrixHTFactVente);
        EditText edtnom = findViewById(R.id.edtNomFactVente);
        EditText edtproduit = findViewById(R.id.edtNomProduitFactVente);

        Button retour = findViewById(R.id.btnRetourCreerFactVente);
        Button creer = findViewById(R.id.btnCreerFactVente);


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent factureVente = new Intent(view.getContext(), FactureVenteActivity.class);
                view.getContext().startActivity(factureVente);
            }
        });

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date laDate = null;
                String dateTmp = edtdate.getText().toString();
                int prixHT = Integer.parseInt(edtprixHT.getText().toString());
                String nom = edtnom.getText().toString();
                String produit = edtproduit.getText().toString();

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
                try {
                    laDate = format.parse(dateTmp);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                db.open();
                Factures factures = new Factures(type, laDate, prixHT, nom, produit);
                Log.i("Main", "Creer Facture" + factures.toString());
                db.ajoutFacture(factures);
                db.close();
                Intent factureVente = new Intent(view.getContext(), FactureVenteActivity.class);
                view.getContext().startActivity(factureVente);
            }
        });

    }
}
