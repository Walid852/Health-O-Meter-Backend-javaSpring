package com.example.projectdeploy.Disease.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Medicine implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    Boolean isDeleted=false;
    Boolean isNotified=true;
    private Date startDate;
    private Date endDate;//لازم يقول تاريخ معين وبعدين يجدده عادى
    private int numberOfDays;//لو مش عايز يقول تاريخ انتهى يقول عدد الايام
    private int numberOfTakesPerDay;
    @JsonIgnore
    @Autowired
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    private Disease disease;

    public Medicine(String name,Boolean isNotified, Date startDate,int numberOfDays, int numberOfTakesPerDay, Disease disease) {
        this.name = name;
        this.isNotified = isNotified;
        this.startDate = startDate;
        this.numberOfDays = numberOfDays;
        this.numberOfTakesPerDay = numberOfTakesPerDay;
        this.disease = disease;
    }
}
