package com.example.projectdeploy.Donate.Model;

import com.example.projectdeploy.MedicalInformation.BloodType;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;
@Data
public class Candidate {
    UUID medicalInformationId;
    String nationalId;
    String name;
    String Username;
    BloodType bloodType;
    String PhoneNumber;
    String Photo=null;
    Status status;
    Date LastUpdateDate=null;
    Date dateOfArrival=null;
}
