package com.advancecst.advance.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE_ENTITE")
@DiscriminatorValue("PERSONNE")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("Technical ID")
    private Long id; // Identfiant technique

    @NotNull
    @ApiModelProperty("Last name of the employee")
    private String lastName;

    @NotNull
    @ApiModelProperty("First name of the employee")
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL)
    private Identification identification;

    public Person() {
    }

    public Person(String lastName, String firstName, Identification identification) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.identification = identification;
    }

    public Long getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Identification getIdentification() {
        return this.identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Set<Phone> getPhones() {
        return this.identification.getPhones();
    }

    public Set<Adress> getAdress() {
        return this.identification.getAdresses();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName)
                && Objects.equals(identification, person.identification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, identification);
    }

}
