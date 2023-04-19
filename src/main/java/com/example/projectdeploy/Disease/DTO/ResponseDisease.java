package com.example.projectdeploy.Disease.DTO;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class ResponseDisease {
    private UUID DiseaseId;
    private UUID medicalInformationId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Boolean isCured;
}


