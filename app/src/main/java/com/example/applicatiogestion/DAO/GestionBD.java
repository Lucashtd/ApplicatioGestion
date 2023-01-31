package com.example.applicatiogestion.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicatiogestion.metier.Produits;

import java.util.ArrayList;
import java.util.List;

public class GestionBD {
    BDHelper bdHelper;
    SQLiteDatabase db;

    public GestionBD(Context context){
        this.bdHelper = new BDHelper(context, "gestion", null, 1);
    }

    public void open(){
        db = bdHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public void ajoutProduit(Produits produit){
        ContentValues cv = new ContentValues();

        cv.put("nomproduit", produit.getNom());
        cv.put("prixunitaire", produit.getPrixunitaire());
        cv.put("ref", produit.getRef());

        db.insert("produits", null, cv);
    }

    public int testBase(){
        String req = "select count(*) from produits";
        Cursor cursor = db.rawQuery(req, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public List<Produits> getProduits(){
        List<Produits> lesProduits = new ArrayList<Produits>();
        String req = "Select * from produits";
        Cursor cursor = db.rawQuery(req, null, null);

        while(cursor.moveToNext()){
            String leNom = cursor.getString(0);
            int lePrix = cursor.getInt(1);
            String laRef = cursor.getString(2);
            Produits unProduit = new Produits(leNom, lePrix, laRef);
            lesProduits.add(unProduit);
        }
        return lesProduits;
    }

    public void updateProduit(String leNom, int lePrix, String laRef){

        ContentValues cv = new ContentValues();
        cv.put("nomproduit", leNom);
        cv.put("prixunitaire", lePrix);
        cv.put("ref", laRef);

        db.update("produits", cv, "nomproduit=?", new String[]{leNom});
    }

    public void supprimerProduit(String leNom){
        db.delete("produits", "nomproduit = ?", new String[]{leNom});
    }

}
