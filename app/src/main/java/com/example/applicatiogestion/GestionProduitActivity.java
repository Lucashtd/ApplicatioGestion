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
import com.example.applicatiogestion.metier.Produits;
import com.example.applicatiogestion.support.ProduitAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionProduitActivity  extends AppCompatActivity {
    GestionBD db = new GestionBD(this);
    List<Produits> lesProduits = new ArrayList<Produits>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_produits);
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;


        Button btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(main);
            }
        });

        Button btnCreationProduit = findViewById(R.id.btnCreerProduit);
        btnCreationProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creerProd = new Intent(view.getContext(), CreationProduitActivity.class);
                view.getContext().startActivity(creerProd);
            }
        });

        db.open();

        initDonnees();

        adapter = new ProduitAdapter(lesProduits);
        recyclerView = findViewById(R.id.rcvProduits);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        List<Produits> testDB = new ArrayList<Produits>();
        for(int i = 0; i < testDB.size(); i++){
            Log.i("Main ", "Test BD : " + testDB.get(i).toString());
        }


        db.close();

    }

    private void initDonnees(){
        Log.i("Main : ", "testBase : "+ db.testBase());
        if(db.testBase() == 0){
            String fichier = lectureFichierLocale();
            recupProduit(fichier);
        } else {
            lesProduits = db.getProduits();
            Log.i("Main ", "Liste produits : " + lesProduits);
        }
    }

    private void recupProduit(String fichier){
        Produits unProduit = null;
        String leNom, laRef;
        int lePrix;
        JSONArray jsa = null;
        JSONObject jsProduit, jsObj;

        if(fichier != null){
            try {
                jsObj = new JSONObject(fichier);
                jsa =jsObj.getJSONArray("lesProduits");

                for (int i = 0; i < jsa.length(); i++){
                    jsProduit = (JSONObject) jsa.get(i);
                    leNom = jsProduit.getString("nomproduit");
                    lePrix = jsProduit.getInt("prixunitaire");
                    laRef = jsProduit.getString("ref");

                    unProduit = new Produits(leNom, lePrix, laRef);

                    lesProduits.add(unProduit);
                    db.ajoutProduit(unProduit);
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
        br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.lesproduits)));
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
