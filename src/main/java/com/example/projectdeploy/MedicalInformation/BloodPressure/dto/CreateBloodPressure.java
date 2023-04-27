package com.example.projectdeploy.MedicalInformation.BloodPressure.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class CreateBloodPressure {
    private @NotNull UUID medicalInfoId;
    private int systolic=-1;
    private int diastolic=-1;
    private Date date;
    private Time time;
}
