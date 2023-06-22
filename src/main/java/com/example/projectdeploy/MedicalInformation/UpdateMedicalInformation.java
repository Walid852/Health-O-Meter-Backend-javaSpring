package com.example.projectdeploy.MedicalInformation;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class UpdateMedicalInformation {
    private UUID medicalInfoId;
    private BloodType bloodType;
    public int CurrentWeight;
    public int CurrentHeight;
    int hemoglobin=14;
    boolean isFever=false;
    Boolean haveAids=false;
    Boolean hepatitis_B=false;
    Boolean hepatitis_C=false;
    Boolean haveMalaria=false;
    Boolean haveSyphilis=false;
    Boolean haveSevereAnemia=false;
    Boolean haveCancer=false;
    Boolean haveDiabetes=false;
    Boolean haveHighBloodPressure=false;
    Boolean haveGeneticBloodDiseases=false;
    Boolean haveAbilityToDonate=false;
}
