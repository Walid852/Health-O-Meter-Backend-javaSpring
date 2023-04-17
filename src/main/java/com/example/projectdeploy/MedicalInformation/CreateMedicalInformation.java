package com.example.projectdeploy.MedicalInformation;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateMedicalInformation {
    private @NotNull UUID UserId;
    private UUID memberId;
    private BloodType bloodType;
    private int numOfCubs;
    public int CurrentWeight;
    public int CurrentHeight;

}
