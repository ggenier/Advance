package com.advancecst.advance.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.advancecst.advance.util.*;

@Entity
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // Identfiant technique

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;

    // Commentaire sur la formation
    private String comment;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate EndDate;

    @NotNull
    private boolean certifying;

    /*
     * Pas de lien de relation avec TrainingCenter, car TrainingCenter contient
     * uniquement la liste des center de trainings indépendement des trainings
     * données
     */
    @NotNull
    private TrainingCenter center;

    // L'adresse de la formation peut être différente de celle du centre
    @NotNull
    private Adress trainingAdress;

    public Training() {
    }

    public Training(TrainingType trainingType, String startDate, String EndDate, TrainingCenter center,
            Adress trainingAdress, boolean certifying, String comment) {
        this.trainingType = trainingType;
        this.startDate = TransfoDates.string2LocalDate(startDate);
        this.EndDate = TransfoDates.string2LocalDate(EndDate);
        this.center = center;
        this.trainingAdress = trainingAdress;
        this.certifying = certifying;
        this.comment = comment;
    }

    public TrainingType getTrainingType() {
        return this.trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDate getDateDeDebut() {
        return this.startDate;
    }

    public void setDateDeDebut(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDateDeFin() {
        return this.EndDate;
    }

    public void setDateDeFin(LocalDate EndDate) {
        this.EndDate = EndDate;
    }

    public TrainingCenter getOrganisme() {
        return this.center;
    }

    public void setOrganisme(TrainingCenter center) {
        this.center = center;
    }

    public Adress gettrainingAdress() {
        return this.trainingAdress;
    }

    public void settrainingAdress(Adress trainingAdress) {
        this.trainingAdress = trainingAdress;
    }

    public Long getId() {
        return this.id;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCertifying() {
        return this.certifying;
    }

    public boolean getCertifying() {
        return this.certifying;
    }

    public void setCertifying(boolean certifying) {
        this.certifying = certifying;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Training)) {
            return false;
        }
        Training training = (Training) o;
        return Objects.equals(id, training.id) && Objects.equals(trainingType, training.trainingType)
                && Objects.equals(startDate, training.startDate) && Objects.equals(EndDate, training.EndDate)
                && Objects.equals(center, training.center) && Objects.equals(trainingAdress, training.trainingAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainingType, startDate, EndDate, center, trainingAdress);
    }

}