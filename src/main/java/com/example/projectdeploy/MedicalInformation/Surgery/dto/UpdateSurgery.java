package com.example.projectdeploy.MedicalInformation.Surgery.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateSurgery {
    private UUID id;
    private String name;
    private String bodyMember;
    //private Date surgeryDate;
}
