/* Classe d'identification contenant l'ensemble des Adress/téléphones d'une person*/

package com.advancecst.advance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Identification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // Identfiant technique

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @LazyCollection(LazyCollectionOption.FALSE)
    // @OrderBy("typeAdress")
    private Set<Adress> adresses;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @LazyCollection(LazyCollectionOption.FALSE)
    // @OrderBy("typePhone")
    private Set<Phone> phones = new HashSet<>();

    public Identification() {
    }

    public Identification(Set<Adress> adresses, Set<Phone> phones) {
        this.adresses = adresses;
        this.phones = phones;
    }

    public Long getId() {
        return this.id;
    }

    public Set<Adress> getAdresses() {
        return this.adresses;
    }

    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }

    public Set<Phone> getPhones() {
        return this.phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", Adress='" + getAdresses() + "'" + ", phones='" + getPhones() + "'"
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Identification)) {
            return false;
        }
        Identification identification = (Identification) o;
        return Objects.equals(id, identification.id) && Objects.equals(adresses, identification.adresses)
                && Objects.equals(phones, identification.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adresses, phones);
    }

}
