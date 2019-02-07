package com.advancecst.advance.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.advancecst.advance.util.*;

@Entity
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // Identfiant technique

    @NotNull
    @Enumerated(EnumType.STRING)
    private AdressType adressType;

    private String flatNumber;

    private String number;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    @NotNull
    private String city;

    private String digitalCode;

    public Adress() {

    }

    public Adress(AdressType adressType, String flatNumber, String number, String street, String zipCode, String city,
            String digitalCode) {
        this.adressType = adressType;
        this.flatNumber = flatNumber;
        this.number = number;
        this.street = street.toUpperCase();
        this.zipCode = zipCode;
        this.city = city.toUpperCase();
        this.digitalCode = digitalCode;
    }

    public Long getId() {
        return this.id;
    }

    public AdressType getAdressType() {
        return this.adressType;
    }

    public void setAdressType(AdressType adressType) {
        this.adressType = adressType;
    }

    public String getFlatNumber() {
        return this.flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return this.street.toUpperCase();
    }

    public void setStreet(String street) {
        this.street = street.toUpperCase();
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city.toUpperCase();
    }

    public void setCity(String city) {
        this.city = city.toUpperCase();
    }

    public String getDigitalCode() {
        return this.digitalCode;
    }

    public void setDigitalCode(String digitalCode) {
        this.digitalCode = digitalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Adress)) {
            return false;
        }
        Adress Adress = (Adress) o;
        return Objects.equals(adressType, Adress.adressType) && Objects.equals(number, Adress.number)
                && Objects.equals(street, Adress.street) && Objects.equals(zipCode, Adress.zipCode)
                && Objects.equals(city, Adress.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adressType, number, street, zipCode, city);
    }

}