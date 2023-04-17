package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset;

import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class SugarTestRequest {
    UUID medicalInformationId;
    TestPeriod period;
    int read=-1;
    Date date;
    Time time;
    UUID testId;
}
