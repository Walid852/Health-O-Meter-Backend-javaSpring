package com.example.projectdeploy.MedicalInformation.Surgery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class CreateSurgery {
    private @NotNull UUID medicalInformationId;
    private @NotNull String name;
    private @NotNull String bodyMember;
    private Date date;
}
