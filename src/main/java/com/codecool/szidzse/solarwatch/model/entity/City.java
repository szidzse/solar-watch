package com.codecool.szidzse.solarwatch.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double longitude;
    private double latitude;
    private String country;
    private String state;

    public City() {

    }

    public City(String name, double longitude, double latitude, String country, String state) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
