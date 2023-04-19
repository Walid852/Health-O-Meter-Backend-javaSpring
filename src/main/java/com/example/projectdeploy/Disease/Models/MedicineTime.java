package com.example.projectdeploy.Disease.Models;

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
public class MedicineTime implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @Autowired
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    private Medicine medicine;
    private Date date;

    public MedicineTime(Medicine medicine, Date date) {
        this.medicine = medicine;
        this.date = date;
    }
}
