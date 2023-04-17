package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
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
    private MedicalInformation medicalInformation;
    @NotNull
    int readd;

    private Date date;

    private Time time;

    boolean isDeleted=false;
    @NotNull
    @Enumerated(EnumType.STRING)
    TestPeriod testPeriod;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
