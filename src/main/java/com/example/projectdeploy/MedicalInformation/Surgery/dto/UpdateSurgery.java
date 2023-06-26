package com.example.projectdeploy.MedicalInformation.Surgery.dto;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class UpdateSurgery {
    private UUID id;
    private String name;
    private String bodyMember;
    private Date date;
}
