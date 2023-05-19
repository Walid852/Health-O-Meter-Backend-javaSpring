package com.example.projectdeploy.Test.Models;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
@Data
@MappedSuperclass
public class Test{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
     UUID id;
    @JsonIgnore
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
     MedicalInformation medicalInformation;
     Boolean isDeleted=false;
     Date date;
    TypesTest test;

    protected Test(MedicalInformation medicalInformation, Boolean isDeleted, Date date, TypesTest test) {
        this.medicalInformation=medicalInformation;
        this.isDeleted=isDeleted;
        this.date=date;
        this.test=test;
    }


    public Test() {

    }
}
