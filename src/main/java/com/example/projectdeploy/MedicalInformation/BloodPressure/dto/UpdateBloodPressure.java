package com.example.projectdeploy.MedicalInformation.BloodPressure.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class UpdateBloodPressure {
    private UUID bloodPressureId;
    private int systolic=-1;
    private int diastolic=-1;
    private Date date;
    private Time time;
    private AM_PM am_pm;
}
