package com.advancecst.advance.util;

/* Enumération des types d'Adress possibles*/
public enum PhoneType {
    // Objets directement construits
    PERSO("Personel"), PROF("Professionel"), MOBI("Mobile"), AUTR("Autre");

    private String phoneType = "";

    // Constructeur
    PhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String toString() {
        return this.phoneType;
    }
}