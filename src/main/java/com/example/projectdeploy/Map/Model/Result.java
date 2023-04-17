package com.example.projectdeploy.Map.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty("address_components")
    private AddressComponents[] address_components;
    @JsonProperty("formatted_address")
    private String formatted_address;
    @JsonProperty("geometry")
    private Geometry  geometry;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public AddressComponents[] getAddress_components() {
        return address_components;
    }

    public void setAddress_components(AddressComponents[] address_components) {
        this.address_components = address_components;
    }
}
