package com.example.projectdeploy.MedicalInformation.Phobia.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PhobiaRequest {
    private @NotNull UUID medicalInformationId;
    private @NotNull String name;
}
