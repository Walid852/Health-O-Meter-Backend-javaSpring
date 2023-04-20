package com.example.projectdeploy.Donate.Model;

import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.MedicalInformation.BloodType;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
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
public class Donate implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private MedicalInformation RequestorMedicalInformation;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private MedicalInformation DonatorMedicalInformation;
    Date donateDate;
    Boolean isDone=false;
    BloodType bloodType;
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @Autowired
    UserLocation location;
    LocationHierarchical current=LocationHierarchical.street;

    public Donate(MedicalInformation requestorMedicalInformation,Date donateDate,BloodType bloodType,UserLocation location) {
        RequestorMedicalInformation = requestorMedicalInformation;
        this.donateDate = donateDate;
        this.location = location;
        this.bloodType=bloodType;
    }
}
