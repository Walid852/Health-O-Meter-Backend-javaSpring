package com.example.projectdeploy.Map.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class UserLocation implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    private String formatted_address;
    private double lat;
    private double lng;
    private String postal_code;
    private String country;
    private String government;
    private String city;
    private String street;

    public UserLocation(String formatted_address, double lat, double lng, String postal_code, String country, String government, String city, String street) {
        this.formatted_address = formatted_address;
        this.lat = lat;
        this.lng = lng;
        this.postal_code = postal_code;
        this.country = country;
        this.government = government;
        this.city = city;
        this.street = street;
    }

    public UserLocation() {

    }
}
