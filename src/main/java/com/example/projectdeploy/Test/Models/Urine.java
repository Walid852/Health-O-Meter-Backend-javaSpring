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
public class Urine extends Test{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    String Color;
    String Clarity;
    float SpecificGravity;
    boolean UrinePH;
    boolean Protein;
    boolean Glucose;
    boolean Ketone;
    boolean Urinebilirubin;
    boolean Nitrite;
    boolean Crystals;
    boolean Casts;

    public Urine(MedicalInformation medicalInformation, boolean isDeleted, Date date, TypesTest test, String color, String clarity, float specificGravity, boolean urinePH, boolean protein, boolean glucose, boolean ketone, boolean urinebilirubin, boolean nitrite, boolean crystals, boolean casts) {
        super(medicalInformation, isDeleted, date, test);
        Color = color;
        Clarity = clarity;
        SpecificGravity = specificGravity;
        UrinePH = urinePH;
        Protein = protein;
        Glucose = glucose;
        Ketone = ketone;
        Urinebilirubin = urinebilirubin;
        Nitrite = nitrite;
        Crystals = crystals;
        Casts = casts;
    }
    public Urine() {
        super();
    }
}
