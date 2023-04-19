package com.example.projectdeploy.Disease.DTO;

import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
public class MedicineDto {
    private UUID DiseaseId;
    private UUID MedicineId;
    private String name;
    private Date startDate;
    private int numberOfDays;
    private int numberOfTakesPerDay;
    private Boolean isNotified=true;
}
