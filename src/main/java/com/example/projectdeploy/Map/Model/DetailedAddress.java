package com.example.projectdeploy.Map.Model;

public class DetailedAddress {
    private String postal_code;
    private String country;
    private String government;
    private String city;
    private String street;

    public DetailedAddress() {
    }

    public DetailedAddress(String postal_code, String country, String government, String city, String street) {
        this.postal_code = postal_code;
        this.country = country;
        this.government = government;
        this.city = city;
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
