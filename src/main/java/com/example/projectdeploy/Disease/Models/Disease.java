package com.example.projectdeploy.Disease.Models;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Disease implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    @JsonIgnore
    private MedicalInformation medicalInformation;

    @NotNull
    private String name;
    @NotNull
    private String description;
    Boolean isDeleted=false;
    private Date startDate;
    private Date endDate;
    private Boolean isCured;
    private Date date;
    private long daysToCure;

    public Disease(MedicalInformation medicalInformation, String name, String description, Date startDate, Date endDate, Boolean isCured, long daysToCure) {
        this.medicalInformation = medicalInformation;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCured = isCured;
        this.daysToCure=daysToCure;
    }
}
