package com.example.projectdeploy.MedicalInformation.Surgery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateSurgery {
    private @NotNull UUID medicalInformationId;
    private @NotNull String name;
    private @NotNull String bodyMember;
    //private Date surgeryDate;
}
