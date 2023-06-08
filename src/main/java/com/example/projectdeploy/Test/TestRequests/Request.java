package com.example.projectdeploy.Test.TestRequests;

import com.example.projectdeploy.Test.Models.TypesTest;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class Request {
    UUID id;
    UUID medicalInformation;
    Boolean isDeleted=false;
    Date date;
    TypesTest test;
    float Haemoglobin=-1;
    float RedCellsCount=-1;
    float Haematocrit=-1;
    float MCV=-1;
    float MCH=-1;
    float MCHC=-1;
    float PlateletsCount=-1;
    float TotalLeucoCyticCount=-1;
    float NeutroPhils=-1;
    float Lymphoctyes=-1;
    float Monocytes=-1;
    float Eosinophils=-1;
    float NeutrophilsabsoluteCount=-1;
    float LymphocytesAbsoluteCount=-1;
    float Monocytesabsolutecount=-1;
    float Eosinophilsabsolutecount=-1;
    float GammaGT=-1;
    float Bilirubin_Total=-1;
    float Bilirubin_Direct=-1;
    float AST=-1;
    float ALT=-1;
    float Alk=-1;
    float TotalProtein=-1;
    float Albumin=-1;
    float Urea=-1;
    float CreatinineInSerum=-1;
    float UricAcid=-1;
    float WhiteBloodCells=-1;
    float RedCellsDistributionWidth=-1;
    String Color="";
    String Consistency="";
     Boolean FoodParticles;
     Boolean Mucus;
     Boolean Blood=null;
     Boolean Starch;
     Boolean Musclefibers;
     Boolean Vegetables;
     Boolean Protozoa;
     Boolean Ciliates;
    String Clarity="";
    float SpecificGravity=-1;
     Boolean UrinePH;
     Boolean Protein;
     Boolean Glucose;
     Boolean Ketone;
     Boolean Urinebilirubin;
     Boolean Nitrite;
     Boolean Crystals;
     Boolean Casts;

}
