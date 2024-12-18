package com.example.projectdeploy.Doctors.DonationAnalysis.DTO;


import com.example.projectdeploy.MedicalInformation.BloodType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTO {

    String city;
    BloodType bloodType;
    double bloodTypeCount;
    double canDonateCount;
    double cannotDonateCount;
    double diabetesCount;
    double cancerCount;
    double aidsCount;
    double malariaCount;
    double highPressureCount;
    double lastDonationCount;
    double geneticCount;
    double severeAnemiaCount;
    double hepatitis_BCount;
    double syphilisCount;

}
