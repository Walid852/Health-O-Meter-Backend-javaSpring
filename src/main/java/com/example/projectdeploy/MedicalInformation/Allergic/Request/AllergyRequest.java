package com.example.projectdeploy.MedicalInformation.Allergic.Request;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class AllergyRequest {

    String name;
    Date  date;
    UUID medicalInformationId;
    UUID AllergyId;
}
