package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model;

import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.AM_PM;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Data
public class SugarBloodTest implements Serializable {
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
    int readd;

    @Column(name = "date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date date;

    private Time time;

    @Enumerated(EnumType.STRING)
    private AM_PM am_pm;

    boolean isDeleted=false;
    @NotNull
    @Enumerated(EnumType.STRING)
    TestPeriod testPeriod;

    @Enumerated(EnumType.STRING)
    private SugarTestCategory sugarTestCategory;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
