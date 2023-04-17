package com.example.projectdeploy.MedicalInformation;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateMedicalInformation {
    private UUID medicalInfoId;
    private BloodType bloodType;
    private int numOfCubs;
    public int CurrentWeight;
    public int CurrentHeight;
}
