package com.example.applicatiogestion.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.applicatiogestion.metier.Factures;
import com.example.applicatiogestion.metier.Produits;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GestionBD {
    BDHelper bdHelper;
    SQLiteDatabase db;

    public GestionBD(Context context){
        this.bdHelper = new BDHelper(context, "gestion", null, 2);
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

    public int testBaseProduit(){
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

    public void ajoutFacture(Factures facture){
        ContentValues cv = new ContentValues();

        cv.put("type", facture.getType());
        cv.put("date", String.valueOf(facture.getDate()));
        cv.put("prix_ht", facture.getPrix_ht());
        cv.put("nom", facture.getNom());
        cv.put("nomProduit", facture.getProduit());

        db.insert("factures", null, cv);
    }

    public int testBaseFacture(){
        String req = "select count(*) from factures";
        Cursor cursor = db.rawQuery(req, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public List<Factures> getFacture(){
        List<Factures> lesFacture = new ArrayList<Factures>();
        String req = "Select * from factures";
        Cursor cursor = db.rawQuery(req, null, null);

        while(cursor.moveToNext()){
            String leType = cursor.getString(0);
            String laDateString = cursor.getString(1);
            Date laDate = null;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
            try {
                laDate = format.parse(laDateString);
            }catch (ParseException e){
                e.printStackTrace();
            }
            int lePrix = cursor.getInt(2);
            String leNom = cursor.getString(3);
            String leNomProduit = cursor.getString(4);
            Factures uneFacture = new Factures(leType, laDate, lePrix, leNom, leNomProduit);
            lesFacture.add(uneFacture);
        }
        return lesFacture;
    }

    public void updateFacture(String leType, Date laDate, int lePrix, String leNom, String leNomProduit){

        ContentValues cv = new ContentValues();
        cv.put("type", leType);
        cv.put("date", String.valueOf(laDate));
        cv.put("prix_ht", lePrix);
        cv.put("nom", leNom);
        cv.put("nomProduit", leNomProduit);

        db.update("factures", cv, "nom=?", new String[]{leNom});
    }

    public void supprimerFacture(String leNom){
        db.delete("factures", "nom = ?", new String[]{leNom});
    }

}
