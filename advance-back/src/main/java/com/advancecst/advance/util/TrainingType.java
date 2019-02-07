package com.advancecst.advance.util;

/* Enumération des trainings disponibles*/
public enum TrainingType {
    // Objets directement construits
    TOIT("Toiture"), ENDU("Enduit"), HUIS("Huisserie");

    private String trainingType = "";

    // Constructeur
    TrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String toString() {
        return this.trainingType;
    }
}