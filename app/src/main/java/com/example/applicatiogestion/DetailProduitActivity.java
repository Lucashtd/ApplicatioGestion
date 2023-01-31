package com.example.applicatiogestion;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.applicatiogestion.DAO.GestionBD;

public class DetailProduitActivity extends AppCompatActivity{
    GestionBD db = new GestionBD(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_produits);
        Bundle extra = getIntent().getExtras();
        String leNom = extra.getString("leNom");
        int laPosition = extra.getInt("laPosition");
        String lePrix = extra.getString("lePrix");
        Log.i("Main : ", "Affiche le prix : " + lePrix);
        String laRef = extra.getString("laRef");
        EditText mtvNomProduit = findViewById(R.id.edtProduitNom);
        EditText mtvPrixProduit = findViewById(R.id.edtProduitPrix);
        EditText mtvRefProduit = findViewById(R.id.edtProduitRef);

        mtvNomProduit.setText(leNom);
        mtvPrixProduit.setText(lePrix);
        mtvRefProduit.setText(laRef);

        Button btnRetourGetProd = findViewById(R.id.btnRetourGestProd2);
        btnRetourGetProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestionProd = new Intent(view.getContext(), GestionProduitActivity.class);
                view.getContext().startActivity(gestionProd);
            }
        });

        Button btnMajProduit = findViewById(R.id.btnValiderCreerProduit);
        btnMajProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                String leNom = mtvNomProduit.getText().toString();
                int lePrix = Integer.parseInt(mtvPrixProduit.getText().toString());
                String laRef = mtvRefProduit.getText().toString();
                Log.i("Main ", "Info recup des edt : " + leNom + " " + lePrix + " " + laRef);
                db.updateProduit(leNom, lePrix, laRef);
                Log.i("Main ", "Maj produit : " + db.getProduits());
                db.close();
            }
        });

        Button btnSupprProduit = findViewById(R.id.btnSupprProduit);
        btnSupprProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                String leNom = mtvNomProduit.getText().toString();
                db.supprimerProduit(leNom);
                db.close();
                Intent gestProd = new Intent(view.getContext(), GestionProduitActivity.class);
                view.getContext().startActivity(gestProd);
            }
        });

    }
}
