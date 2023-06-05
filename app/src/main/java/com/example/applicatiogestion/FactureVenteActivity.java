package com.example.applicatiogestion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicatiogestion.DAO.GestionBD;
import com.example.applicatiogestion.metier.Factures;
import com.example.applicatiogestion.support.FactureVenteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FactureVenteActivity extends AppCompatActivity {
    GestionBD db = new GestionBD(this);
    List<Factures> lesFacture = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facture_vente);
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        Button btnRetourFV = findViewById(R.id.btnRetourFV);
        Button btnCreerFactureFV = findViewById(R.id.btnCreerFactureFV);

        btnCreerFactureFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creerFactureVente = new Intent(view.getContext(), CreationFactureVente.class);
                view.getContext().startActivity(creerFactureVente);
            }
        });

        btnRetourFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retourGF = new Intent(view.getContext(), GestionFactureActivity.class);
                view.getContext().startActivity(retourGF);
            }
        });

        db.open();

        initDonnees();

        adapter = new FactureVenteAdapter(lesFacture);
        recyclerView = findViewById(R.id.rcvFactureVente);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        List<Factures> testDB = new ArrayList<Factures>();
        for(int i = 0; i < testDB.size(); i++){
            Log.i("Main ", "Test BD : " + testDB.get(i).toString());
        }


        db.close();

    }

    private void initDonnees(){
        Log.i("Main : ", "testBase : "+ db.testBaseFacture());
        if(db.testBaseFacture() == 0){
            String fichier = lectureFichierLocale();
            recupProduit(fichier);
        } else {
            lesFacture = db.getFacture();
            Log.i("Main ", "Liste Factures Vente : " + lesFacture);
        }
    }

    private void recupProduit(String fichier){
        Factures uneFacture = null;
        String leType, leNom, leNomProduit;
        int lePrix;
        JSONArray jsa = null;
        JSONObject jsFacture, jsObj;

        if(fichier != null){
            try {
                jsObj = new JSONObject(fichier);
                jsa =jsObj.getJSONArray("lesFactures");

                for (int i = 0; i < jsa.length(); i++){
                    jsFacture = (JSONObject) jsa.get(i);
                    leType = jsFacture.getString("type");
                    String laDateString = jsFacture.getString("date");
                    Date laDate = null;
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
                    try {
                        laDate = format.parse(laDateString);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    lePrix = jsFacture.getInt("prix_ht");
                    leNom = jsFacture.getString("nom");
                    leNomProduit = jsFacture.getString("nomProduit");

                    uneFacture = new Factures(leType, laDate, lePrix, leNom, leNomProduit);

                    lesFacture.add(uneFacture);
                    db.ajoutFacture(uneFacture);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String lectureFichierLocale(){
        StringBuilder builder = new StringBuilder();
        String ligne = null;
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.lesfacturesventes)));
        try {
            while((ligne = br.readLine()) != null){
                builder.append(ligne).append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.i("Main ", "Recup Fichier : " + builder.toString());
        return builder.toString();
    }
}
