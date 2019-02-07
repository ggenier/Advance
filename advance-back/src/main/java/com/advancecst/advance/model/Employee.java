package com.advancecst.advance.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import com.advancecst.advance.util.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@NamedQuery(name = "findByHRId", query = "SELECT s FROM Employee s WHERE s.idEmployee = :idEmployee")

@Entity
@ApiModel(description = "Employee resource representation")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE_ENTITE")
@DiscriminatorValue("SALARIE")
public class Employee extends Person implements Serializable {

   private static final long serialVersionUID = 1L;

   // Identifiant RH du salarié
   @ApiModelProperty("Human resources ID")
   private String idEmployee;

   @XmlJavaTypeAdapter(LocalDateAdapter.class)
   @ApiModelProperty("Date of birth")
   @NotNull
   private LocalDate birthDate;

   @NotNull
   @Enumerated(EnumType.STRING)
   @ApiModelProperty("Type of employee")
   private EmployeeType employeeType;

   @Transient
   @ApiModelProperty("Age of the employee")
   private long age;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
   @ApiModelProperty("List of employee skills")
   private Set<Skill> skills = new HashSet<>();

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
   @ApiModelProperty("List of training employee has been follow")
   private Set<Training> trainings = new HashSet<>();

   public Employee() {
      super();
   }

   public Employee(String idEmployee, String lastName, String firstName, String birthDate, EmployeeType employeeType,
         Identification identification) {
      super(lastName, firstName, identification);
      this.idEmployee = idEmployee;
      this.birthDate = TransfoDates.string2LocalDate(birthDate);
      this.employeeType = employeeType;
   }

   public Employee(String idEmployee, String lastName, String firstName, String birthDate, EmployeeType employeeType,
         Identification identification, Set<Skill> skills, Set<Training> trainings) {
      super(lastName, firstName, identification);
      this.idEmployee = idEmployee;
      this.birthDate = TransfoDates.string2LocalDate(birthDate);
      this.employeeType = employeeType;
      this.skills = skills;
      this.trainings = trainings;
   }

   public Employee(String birthDate) {
      this.birthDate = TransfoDates.string2LocalDate(birthDate);
   }

   public String getIdEmployee() {
      return this.idEmployee;
   }

   public void setIdEmployee(String id) {
      this.idEmployee = id;
   }

   public LocalDate getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
   }

   public EmployeeType getEmployeeType() {
      return this.employeeType;
   }

   public void setEmployeeType(EmployeeType employeeType) {
      this.employeeType = employeeType;
   }

   public Set<Skill> getSkills() {
      return this.skills;
   }

   public void setSkills(Set<Skill> skills) {
      this.skills = skills;
   }

   public Set<Training> getTrainings() {
      return this.trainings;
   }

   public void setTrainings(Set<Training> trainings) {
      this.trainings = trainings;
   }

   public long getAge() {
      return this.age;
   }

   @Override
   public String toString() {
      return "{" + " idEmployee='" + getIdEmployee() + "'" + ", birthDate='" + getBirthDate() + "'" + ", employeeType='"
            + getEmployeeType() + "'" + ", age='" + getAge() + "'" + ", skills='" + getSkills() + "'" + ", trainings='"
            + getTrainings() + "'" + "}";
   }

   @Override
   public boolean equals(Object o) {
      if (o == this)
         return true;
      if (!(o instanceof Employee)) {
         return false;
      }
      Employee employee = (Employee) o;
      return Objects.equals(idEmployee, employee.idEmployee);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(idEmployee);
   }

}
