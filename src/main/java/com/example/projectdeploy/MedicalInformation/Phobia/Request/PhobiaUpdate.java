package com.example.projectdeploy.MedicalInformation.Phobia.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PhobiaUpdate {
    private @NotNull UUID phobiaId;
    private @NotNull String name;
}
