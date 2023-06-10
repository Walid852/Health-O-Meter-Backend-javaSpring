package com.example.projectdeploy.MedicalInformation.BloodPressure.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter @Getter
public class PressureAnalysis {
    double AvgSystolic;
    double AvgDiastolic;
    Date date;
}
