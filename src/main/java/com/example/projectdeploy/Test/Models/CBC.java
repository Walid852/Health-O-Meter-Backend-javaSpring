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
public class CBC extends Test{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    private float Haemoglobin;
    private float RedCellsCount;
    private float Haematocrit;
    private float MCV;
    private float MCH;
    private float MCHC;
    private float PlateletsCount;
    private float TotalLeucoCyticCount;
    private float NeutroPhils;
    private float Lymphoctyes;
    private float Monocytes;
    private float Eosinophils;
    private float NeutrophilsabsoluteCount;
    private float LymphocytesAbsoluteCount;
    private float Monocytesabsolutecount;
    private float Eosinophilsabsolutecount;

    public CBC() {
        super();
    }

    public CBC(MedicalInformation medicalInformation, Boolean isDeleted, Date date, TypesTest test, float haemoglobin, float redCellsCount, float haematocrit, float MCV, float MCH, float MCHC, float plateletsCount, float totalLeucoCyticCount, float neutroPhils, float lymphoctyes, float monocytes, float eosinophils, float neutrophilsabsoluteCount, float lymphocytesAbsoluteCount, float monocytesabsolutecount, float eosinophilsabsolutecount) {
        super(medicalInformation, isDeleted, date, test);
        Haemoglobin = haemoglobin;
        RedCellsCount = redCellsCount;
        Haematocrit = haematocrit;
        this.MCV = MCV;
        this.MCH = MCH;
        this.MCHC = MCHC;
        PlateletsCount = plateletsCount;
        TotalLeucoCyticCount = totalLeucoCyticCount;
        NeutroPhils = neutroPhils;
        Lymphoctyes = lymphoctyes;
        Monocytes = monocytes;
        Eosinophils = eosinophils;
        NeutrophilsabsoluteCount = neutrophilsabsoluteCount;
        LymphocytesAbsoluteCount = lymphocytesAbsoluteCount;
        Monocytesabsolutecount = monocytesabsolutecount;
        Eosinophilsabsolutecount = eosinophilsabsolutecount;
    }
}
