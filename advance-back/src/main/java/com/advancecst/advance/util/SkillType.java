package com.advancecst.advance.util;

/* Enumération des compétences disponibles*/
public enum SkillType {
    // Objets directement construits
    TOIT("Toiture"), ENDU("Enduit"), HUIS("Huisserie");

    private String skillType = "";

    // Constructeur
    SkillType(String skillType) {
        this.skillType = skillType;
    }

    public String toString() {
        return this.skillType;
    }
}