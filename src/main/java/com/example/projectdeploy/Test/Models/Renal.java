package com.example.projectdeploy.Test.Models;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Renal extends Test{
    @Id
    @Column(name = "id", nullable = false)
    UUID id;
     float Urea;
     float CreatinineInSerum;
     float UricAcid;
    @Autowired
    public Renal(float urea, float creatinineInSerum, float uricAcid,MedicalInformation medicalInformation, Boolean isDeleted, Date date, TypesTest test) {
        super(medicalInformation,isDeleted,date,test);

        Urea = urea;
        CreatinineInSerum = creatinineInSerum;
        UricAcid = uricAcid;
    }

    public Renal() {
        super();
    }
}
