package com.advancecst.advance.util;

/* Enumération des types de employee possible*/
public enum EmployeeType {
    // Objets directement construits
    VRP("Commercial"), ADM("Administratif"), OUV("Ouvrier");

    private String employeeType = "";

    // Constructeur
    EmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String toString() {
        return this.employeeType;
    }
}