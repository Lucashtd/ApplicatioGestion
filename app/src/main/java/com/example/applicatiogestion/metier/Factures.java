package com.example.applicatiogestion.metier;

import java.util.Date;

public class Factures {

    private String type = "";
    private Date date;
    private int prix_ht = 0;
    private String nom = "";
    private String nomProduit;

    public Factures(String unType, Date uneDate, int unPrixHt, String unNom, String unProduit){
        this.type = unType;
        this.date = uneDate;
        this.prix_ht = unPrixHt;
        this.nom = unNom;
        this.nomProduit = unProduit;
    }

    public String getType(){ return this.type;}

    public Date getDate(){ return this.date;}

    public int getPrix_ht(){ return this.prix_ht;}

    public String getNom(){ return this.nom;}

    public String getProduit(){ return this.nomProduit;}

    public void setType(String unType){
        this.type = unType;
    }

    public void setDate(Date uneDate){
        this.date = uneDate;
    }

    public void setPrix_ht(int unPrix){
        this.prix_ht = unPrix;
    }

    public void setNom(String unNom){
        this.nom = unNom;
    }

    public void setProduit(String unProduit){
        this.nomProduit = unProduit;
    }

    @Override
    public String toString() {
        return   nom + " / Du : " + date;
    }
}
