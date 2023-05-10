package com.example.applicatiogestion.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String req = "create table produits (nomproduit text, prixunitaire int, ref text);";
        String creerTableFacture = "CREATE TABLE factures (type text, date text, prix_ht int, nom text, nomProduit text);";
        //String creerTableAsso = "CREATE TABLE asso_prod_fact (quantite int, id_produit int, id_facture int, FOREIGN KEY (id_produit) REFERENCES produits(id), FOREIGN KEY (id_fature) REFERENCES factures(id));";
        //String creerTableTaxes = "CREATE TABLE taxes (id INTEGER PRIMARY KEY AUTOINCREMENT, tva int);";


        //String req = "CREATE TABLE produits (id int PRIMARY KEY AUTOINCREMENT, nomproduit text, prixunitaire int, ref text);";
        //String creerTableFacture = "CREATE TABLE factures (id int PRIMARY KEY AUTOINCREMENT, type text, date text, prix_ht int, nom text, nomProduit text);";
        //String creerTableAsso = "CREATE TABLE asso_prod_fact (quantite int, id_produit int, id_facture int, FOREIGN KEY (id_produit) REFERENCES produits(id), FOREIGN KEY (id_fature) REFERENCES factures(id));";
        //String creerTableTaxes = "CREATE TABLE taxes (id INTEGER PRIMARY KEY AUTOINCREMENT, tva int);";
        sqLiteDatabase.execSQL(req);
        sqLiteDatabase.execSQL(creerTableFacture);
        //sqLiteDatabase.execSQL(creerTableAsso);
        //sqLiteDatabase.execSQL(creerTableTaxes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS factures");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produits");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS asso_prod_fact");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS taxes");
        onCreate(sqLiteDatabase);
    }
}
