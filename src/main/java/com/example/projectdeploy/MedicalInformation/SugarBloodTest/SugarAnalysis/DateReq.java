package com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
public class DateReq {
    Date from;
    Date to;
    UUID medicalId;
}