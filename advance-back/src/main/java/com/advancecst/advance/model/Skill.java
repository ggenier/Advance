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
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // Identfiant technique

    @NotNull
    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    private boolean certified; // Est ce que le salarié est certifié sur cette compétence

    public Skill() {
    }

    public Skill(SkillType skillType, boolean certified) {
        this.skillType = skillType;
        this.certified = certified;
    }

    public Long getId() {
        return this.id;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public boolean isCertifie() {
        return this.certified;
    }

    public void setCertifie(boolean certified) {
        this.certified = certified;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(skillType, skill.skillType)
                && certified == skill.certified;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillType, certified);
    }

}