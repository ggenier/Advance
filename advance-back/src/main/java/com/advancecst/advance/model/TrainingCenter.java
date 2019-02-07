package com.advancecst.advance.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Training center reperesentation")
public class TrainingCenter implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id; // Identfiant technique

   @ApiModelProperty("Trainig identification")
   @NotNull
   @OneToOne(cascade = CascadeType.ALL)
   private Identification identification;

   @NotNull
   @ApiModelProperty("Description about the training center")
   private String description;

   @ApiModelProperty("Trainings executed in this center")
   @OneToMany(cascade = CascadeType.ALL)
   Set<Training> training = new HashSet<>();

   public TrainingCenter() {
   }

   public TrainingCenter(Identification identification, String description) {
      this.identification = identification;
      this.description = description;
   }

   public Long getId() {
      return this.id;
   }

   public Identification getIdentification() {
      return this.identification;
   }

   public void setIdentification(Identification identification) {
      this.identification = identification;
   }

   public String getLibelle() {
      return this.description;
   }

   public void setLibelle(String description) {
      this.description = description;
   }

   @Override
   public boolean equals(Object o) {
      if (o == this)
         return true;
      if (!(o instanceof TrainingCenter)) {
         return false;
      }
      TrainingCenter trainingOragnization = (TrainingCenter) o;
      return Objects.equals(id, trainingOragnization.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }

}