package com.advancecst.advance.util;

/* Enumération des types d'Adress possibles*/
public enum AdressType {
    // Objets directement construits
    PERSO("Personelle"), PROF("Professionelle"), OTHE("Autre");

    private String adressType = "";

    // Constructeur
    AdressType(String adressType) {
        this.adressType = adressType;
    }

    public String toString() {
        return this.adressType;
    }
}