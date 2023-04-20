package com.example.projectdeploy.Donate.Model;

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
public class DonateNotified implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private MedicalInformation MedicalInformation;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private Donate donate;
    Status status=Status.Pending;
    Date LastUpdateDate;
    Date dateOfArrival=null;
    public DonateNotified(MedicalInformation medicalInformation, Donate donate, Status status, Date lastUpdateDate) {
        MedicalInformation = medicalInformation;
        this.donate = donate;
        this.status = status;
        LastUpdateDate = lastUpdateDate;
    }
}
