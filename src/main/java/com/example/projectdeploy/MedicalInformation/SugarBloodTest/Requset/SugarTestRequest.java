package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset;

import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.AM_PM;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class SugarTestRequest {
    private UUID medicalInformationId;
    private TestPeriod period;
    private int read=-1;
    private Date date;
    private Time time;
    private AM_PM am_pm;
    private UUID testId;
}
