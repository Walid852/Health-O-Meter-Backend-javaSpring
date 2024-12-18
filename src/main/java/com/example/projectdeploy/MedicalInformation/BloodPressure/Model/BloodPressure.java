package com.example.projectdeploy.MedicalInformation.BloodPressure.Model;

import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.AM_PM;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BloodPressure implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    @Autowired
    MedicalInformation medicalInformation;

    @NotNull
    int systolic;
    @NotNull
    int diastolic;
    boolean isDeleted=false;

    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    private Time time;

    @Enumerated(EnumType.STRING)
    private AM_PM am_pm;

    @Enumerated(EnumType.STRING)
    private BloodPressureCategory bloodPressureCategory;



}
