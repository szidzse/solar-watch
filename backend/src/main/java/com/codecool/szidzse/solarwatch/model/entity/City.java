package com.codecool.szidzse.solarwatch.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SunriseSunset> sunriseSunsetList = new ArrayList<>();

    public City(Long id, String name, String state, String country, BigDecimal longitude, BigDecimal latitude) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void addSunriseSunset(SunriseSunset sunriseSunset) {
        sunriseSunsetList.add(sunriseSunset);
        sunriseSunset.setCity(this);
    }

    public void removeSunriseSunset(SunriseSunset sunriseSunset) {
        sunriseSunsetList.remove(sunriseSunset);
        sunriseSunset.setCity(null);
    }
}
