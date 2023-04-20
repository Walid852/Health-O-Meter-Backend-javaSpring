package com.example.projectdeploy.Test.Models;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Stool extends Test{
    @Id
    @Column(name = "id", nullable = false)
    UUID id;
    String Color;
    String Consistency;
    Boolean FoodParticles;
    Boolean Mucus;
    Boolean Blood;
    Boolean Starch;
    Boolean Musclefibers;
    Boolean Vegetables;
    Boolean Protozoa;
    Boolean Ciliates;

    public Stool() {
        super();
    }

    public Stool(MedicalInformation medicalInformation, Boolean isDeleted, Date date, TypesTest test, String color, String consistency, Boolean foodParticles, Boolean mucus, Boolean blood, Boolean starch, Boolean musclefibers, Boolean vegetables, Boolean protozoa, Boolean ciliates) {
        super(medicalInformation, isDeleted, date, test);
        Color = color;
        Consistency = consistency;
        FoodParticles = foodParticles;
        Mucus = mucus;
        Blood = blood;
        Starch = starch;
        Musclefibers = musclefibers;
        Vegetables = vegetables;
        Protozoa = protozoa;
        Ciliates = ciliates;
    }
}
