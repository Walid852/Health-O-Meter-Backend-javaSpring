package com.example.projectdeploy.MedicalInformation.BloodPressure.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateBloodPressure {
    private UUID bloodPressureId;
    private int systolic=-1;
    private int diastolic=-1;
}
