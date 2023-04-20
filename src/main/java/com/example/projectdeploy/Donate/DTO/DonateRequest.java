package com.example.projectdeploy.Donate.DTO;

import com.example.projectdeploy.MedicalInformation.BloodType;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class DonateRequest {
    UUID requesterMedicalInformationId;
    BloodType bloodType;
    String lating;
    Date donateDate;
}
