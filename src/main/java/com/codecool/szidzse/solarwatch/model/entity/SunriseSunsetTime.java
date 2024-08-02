package com.codecool.szidzse.solarwatch.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SunriseSunsetTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private City city;
    private LocalDate date;
    private String sunrise;
    private String sunset;

    public SunriseSunsetTime() {

    }

    public SunriseSunsetTime(City city, LocalDate date, String sunrise, String sunset) {
        this.city = city;
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
