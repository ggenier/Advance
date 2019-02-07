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
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // Identfiant technique

    @NotNull
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    private String number;

    public Phone() {

    }

    public Phone(PhoneType phoneType, String number) {
        this.phoneType = phoneType;
        this.number = number;
    }

    public PhoneType getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getNumero() {
        return this.number;
    }

    public void setNumero(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Phone)) {
            return false;
        }
        Phone phone = (Phone) o;
        return Objects.equals(phoneType, phone.phoneType) && Objects.equals(number, phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneType, number);
    }

}