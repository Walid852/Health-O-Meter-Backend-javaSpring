package com.example.projectdeploy.MedicalInformation.Surgery.Model;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Surgery {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    MedicalInformation medicalInformation;

    @NotNull
    private String name;

    private String bodyMember;

    boolean isDeleted=false;

    @Column(name = "date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;

    /*@Column(name = "date")
    @DateTimeFormat
    private Date surgeryDate;*/


}
