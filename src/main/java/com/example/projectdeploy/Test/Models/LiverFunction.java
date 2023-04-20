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
public class LiverFunction extends Test{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    float GammaGT;
    float Bilirubin_Total;
    float Bilirubin_Direct;
    float AST;
    float ALT;
    float Alk;
    float TotalProtein;
    float Albumin;

    public LiverFunction() {
        super();
    }

    public LiverFunction(MedicalInformation medicalInformation, Boolean isDeleted, Date date, TypesTest test, float gammaGT, float bilirubin_Total, float bilirubin_Direct, float AST, float ALT, float alk, float totalProtein, float albumin) {
        super(medicalInformation, isDeleted, date, test);
        GammaGT = gammaGT;
        Bilirubin_Total = bilirubin_Total;
        Bilirubin_Direct = bilirubin_Direct;
        this.AST = AST;
        this.ALT = ALT;
        Alk = alk;
        TotalProtein = totalProtein;
        Albumin = albumin;
    }
}
