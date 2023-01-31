package com.example.applicatiogestion.metier;

public class Produits {

    private String nom = "";
    private int prixunitaire = 0;
    private String ref = "";

    public Produits(String unNom, int unPrix, String uneRef){
        this.nom = unNom;
        this.prixunitaire = unPrix;
        this.ref = uneRef;
    }

    public String getNom() {
        return nom;
    }

    public int getPrixunitaire() {
        return prixunitaire;
    }

    public String getRef() {
        return ref;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrixunitaire(int prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String toString(){
        return nom + " " + prixunitaire + "€ " + ref;
    }
}
