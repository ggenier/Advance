package com.advancecst.advance.util;

public enum MoyenReglement {
  // Objets directement construits
  CHQ("Chèque"), PRL("Prélèvement"), VIR("VIREMENT");

  private String moyenReglement = "";

  // Constructeur
  MoyenReglement(String moyenReglement) {
    this.moyenReglement = moyenReglement;
  }

  public String toString() {
    return this.moyenReglement;
  }
}