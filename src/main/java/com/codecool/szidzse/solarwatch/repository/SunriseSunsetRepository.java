package com.codecool.szidzse.solarwatch.repository;

import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Long> {
    Optional<SunriseSunset> findByCityAndDate(City city, LocalDate date);
}
