package com.example.projectdeploy.Donate.DTO;

import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import lombok.Data;

import java.util.List;

@Data
public class Resultt {
    Donate donate;
    List<MedicalInformation> medicalInformationList;
    List<DonateNotified> donateNotified;
}
