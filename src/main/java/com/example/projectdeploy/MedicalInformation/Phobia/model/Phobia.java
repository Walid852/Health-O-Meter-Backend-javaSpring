package com.example.projectdeploy.MedicalInformation.Phobia.model;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
public class Phobia implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private MedicalInformation medicalInformation;
    boolean isDeleted=false;

    @Column(name = "date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
